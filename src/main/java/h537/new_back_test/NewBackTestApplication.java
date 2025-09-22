package h537.new_back_test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("h537.new_back_test.mapper")   // 扫接口
@SpringBootApplication
public class NewBackTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewBackTestApplication.class, args);
    }

}
