package h537.new_back_test.controller;

import h537.new_back_test.entity.boardGame;
import h537.new_back_test.entity.uploadGame;
import h537.new_back_test.service.homePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class homePage {

    @Autowired
    homePageService homePageService;


    @GetMapping("/get_img")//通过id获取图片
    public String getImgById(@RequestParam String id) throws IOException {
        System.out.println("getImgById");
        String url = "http://8.138.4.198:8080/" + id + ".jpg";
        System.out.println("url");
        return url;
    }

    @GetMapping("/get_all_img")//一次获得10张图片图片
    public List<boardGame> getAllImgById(@RequestParam int index) throws IOException {
        List<boardGame> list=homePageService.getAllImgById(index);
        return list;
    }

    @PostMapping("/post_boardGame")//上传图片，内容是二进制的图片和图片的名字
    public String postImgById(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) throws IOException {
        homePageService.post(name);
        String uploadPath = "C:\\Users\\31683\\Desktop\\img"; // 示例路径，请根据实际情况修改
        File destFile = new File(uploadPath, file.getOriginalFilename());

        // 确保目录存在
        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }

        // 将文件保存到服务器
        file.transferTo(destFile);
        return "ok";

    }
}
