package h537.first.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;



@RestController
@Slf4j


public class first_test {


    @GetMapping("/get_img")
    public String getImg() throws IOException {
        log.info("666");
        return "6666666666666666";

    }
}
