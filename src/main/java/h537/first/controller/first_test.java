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
    public String getImgById(@RequestParam String id) throws IOException {
        log.info("get img by {}", id);
        String url = "http://8.138.4.198:8080/" + id + ".jpg";
        return url;
    }
<<<<<<< Updated upstream
=======

    @PostMapping("/post_img")
    public String postImgById(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) {
        log.info("post img, name: {}", name);

        // 检查文件是否为空
        if (file.isEmpty()) {
            return "文件为空";
        }

        try {
            // 定义文件保存路径
            String uploadPath = "C:\\Users\\31683\\Desktop\\img"; // 示例路径，请根据实际情况修改
            File destFile = new File(uploadPath, file.getOriginalFilename());

            // 确保目录存在
            if (!destFile.getParentFile().exists()) {
                destFile.getParentFile().mkdirs();
            }

            // 将文件保存到服务器
            file.transferTo(destFile);

            log.info("文件保存成功: {}", destFile.getAbsolutePath());
            return "ok";
        } catch (IOException e) {
            log.error("文件保存失败", e);
            return "保存失败";
        }
    }
>>>>>>> Stashed changes
}
