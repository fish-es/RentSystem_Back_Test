package h537.new_back_test.controller.test;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailControllerTest {

    private final JavaMailSender mailSender;   // 自动注入

    @GetMapping("/send")
    public String send() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("3168347190@qq.com");        // 必须和 yml 里 username 一致
        msg.setTo("3427443116@qq.com");
        msg.setSubject("Spring Boot 纯文本");
        msg.setText("你好，这是 Spring Boot 发来的邮件！");
        mailSender.send(msg);
        System.out.println("成功");
        return "ok";
    }
}