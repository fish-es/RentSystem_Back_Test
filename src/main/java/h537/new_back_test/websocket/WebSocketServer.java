package h537.new_back_test.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import h537.new_back_test.Configure.SpringContextHolder;
import h537.new_back_test.entity.Chat.ChatMsg;
import h537.new_back_test.mapper.ChatMessagesMapper;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/api/ws/{user}")
public class WebSocketServer {

    private static final Map<Integer, Session> ONLINE = new ConcurrentHashMap<>();

    private ChatMessagesMapper chatMessagesMapper;

    @OnOpen
    public void onOpen(Session session, @PathParam("user") int user) {
        // 第一次上线时把 bean 拿回来
        if (chatMessagesMapper == null) {
            chatMessagesMapper = SpringContextHolder.getBean(ChatMessagesMapper.class);
        }
        ONLINE.put(user, session);
        System.out.println(user + "上线");
    }

    @OnMessage
    public void onMessage(String json) {
        try {

            ChatMsg msg = new ObjectMapper().readValue(json, ChatMsg.class);
            String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            msg.setSendTime(currentTime);

            System.out.printf("发送 %s -> %s : %s%n", msg.sendUid, msg.acceptUid, msg.content);
            chatMessagesMapper.send(msg);
            Session target = ONLINE.get(msg.acceptUid);
            if (target != null && target.isOpen()) {
                // 把**整句话**发回去，前端直接显示
                String Json = new ObjectMapper().writeValueAsString(msg);
                target.getBasicRemote().sendText(Json);
            }
            else{
                System.out.println(msg.acceptUid+"不在线");
            }
        } catch (IOException e) {
            System.out.println("发失败：" + e.getMessage());
        }
    }

    @OnClose
    public void onClose(@PathParam("user") String user) {
        ONLINE.remove(user);
        System.out.println(user + "下线");
    }

    @OnError
    public void onError(Session s, Throwable e) {
        System.out.println("WS 异常：" + e.getMessage());
    }
}