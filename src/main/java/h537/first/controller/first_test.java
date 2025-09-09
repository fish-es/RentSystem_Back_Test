package h537.first.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

//needed remove
//import java.nio.file.Files;
//import java.nio.file.Path;

import java.io.InputStream;
import java.util.Base64;



@RestController
@Slf4j


public class first_test {


    @GetMapping("/get_img")
    public String getImg() throws IOException {
        log.info("get img");

//        String imagePath = "src/main/resources/static/1.jpg";
//        Path path = Path.of(imagePath);

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("static/1.jpg");
        if (inputStream == null) {
            throw new IOException("File not found: static/1.jpg");
        }

        // Read the file content into a byte array1

//        byte[] fileContent = Files.readAllBytes(path);

        byte[] fileContent = new byte[inputStream.available()];
        inputStream.read(fileContent);
        inputStream.close();

        // Encode the byte array to a Base64 string11111
        String base64EncodedString = Base64.getEncoder().encodeToString(fileContent);

        // Print Base64 string to the console
        System.out.println(base64EncodedString);

        return base64EncodedString;

    }
}
