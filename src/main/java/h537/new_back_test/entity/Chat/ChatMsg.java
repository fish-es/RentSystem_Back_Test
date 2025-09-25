package h537.new_back_test.entity.Chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMsg {
    public int sendUid; // 发送方
    public int acceptUid;   // 接收方
    public String content; // 内容
    public String sendTime;
}