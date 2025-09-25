package h537.new_back_test.mapper;

import h537.new_back_test.entity.Chat.ChatMsg;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatMessagesMapper {
    @Insert("insert into borrow_test.chat_messages (send_uid, accept_uid, content,send_time) VALUES (#{sendUid},#{acceptUid},#{content},#{sendTime})")
    void send(ChatMsg msg);
}
