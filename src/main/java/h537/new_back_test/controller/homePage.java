package h537.new_back_test.controller;

import h537.new_back_test.entity.boardGame;
import h537.new_back_test.entity.uploadGame;
import h537.new_back_test.service.homePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
public class homePage {

    @Autowired
    homePageService homePageService;

    @GetMapping("/test")
    public String test(){
        return "你能成功访问";
    }

    //内部使用，获得单张图片
    @GetMapping("/get_img/{id}")
    public ResponseEntity<byte[]> imgById(@PathVariable("id") int id) {
        System.out.println("进入get_img方法");

        // 指定存储路径
        String uploadPath = "/img";
        String fileName = id + ".jpg";
        File file = new File(uploadPath, fileName);

        // 检查文件是否存在
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        try {
            // 读取文件内容到字节数组
            byte[] imageBytes = Files.readAllBytes(file.toPath());

            // 设置HTTP头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            headers.setContentLength(imageBytes.length);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(imageBytes);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    //外部使用，返回10款桌游的信息
    @GetMapping("/get_all_img")//一次获得10张图片图片
    public List<boardGame> getAllImgById(@RequestParam int index) throws IOException {
        List<boardGame> list=homePageService.getAllImgById(index);
        return list;
    }

    //外部使用，搜索桌游
    @GetMapping("/search_img")
    public List<boardGame> searchImgByValue(@RequestParam String value) throws IOException {
        List<boardGame> list=homePageService.searchImgByValue(value);
        return list;
    }

    //外部使用，上传桌游
    @PostMapping("/post_boardGame")//上传图片，内容是二进制的图片和图片的名字
    public String postImgById(@RequestParam("file") MultipartFile file,@RequestParam("name") String name) throws IOException {
        homePageService.post(file,name);
        return "ok";
    }

}
