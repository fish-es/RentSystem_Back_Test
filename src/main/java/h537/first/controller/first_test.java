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
        log.info("get img");
        String imagePath = "E:\\002code\\003idea\\idea_code\\springboot\\wx-test\\borrow_test\\src\\main\\resources\\static\\1.jpg";
        Path path = Path.of(imagePath);

        // Read the file content into a byte array1
        byte[] fileContent = Files.readAllBytes(path);

        // Encode the byte array to a Base64 string11111
        String base64EncodedString = Base64.getEncoder().encodeToString(fileContent);

        // Print Base64 string to the console
        System.out.println(base64EncodedString);

        return base64EncodedString;

    }
}
