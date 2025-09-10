package h537.first.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@RestController
@Slf4j


public class first_test {


//    @GetMapping("/get_img")
//    public String getImg() throws IOException {
//        log.info("get img");
//
//        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("static/1.jpg");
//        if (inputStream == null) {
//            throw new IOException("File not found: static/4568.jpg");
//        }
//        // Read the file content into a byte array1
//
//        byte[] fileContent = inputStream.readAllBytes();
//        inputStream.read(fileContent);
//        inputStream.close();
//
//        // Encode the byte array to a Base64 string11111
//        String base64EncodedString = Base64.getEncoder().encodeToString(fileContent);
//
//        // Print Base64 string to the console
//        System.out.println(base64EncodedString);
//
//
//        return base64EncodedString;
//    }
    @GetMapping("/get_img")
    public String getImg() throws IOException {
        log.info("666");
        return "6666666666666666";

    }
}
