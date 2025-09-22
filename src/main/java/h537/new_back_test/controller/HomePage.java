package h537.new_back_test.controller;

import h537.new_back_test.entity.Game_entity.BoardGame;
import h537.new_back_test.entity.Game_entity.BoardGameDto;
import h537.new_back_test.entity.User_entity.UserLoginDto;
import h537.new_back_test.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api")
public class HomePage {

    @Autowired
    HomePageService homePageService;



    @GetMapping("/test")
    public String test(){
        return "你能成功访问";
    }


    //内部使用，获得单张图片
    @GetMapping("/get_img/{id}")
    public ResponseEntity<byte[]> imgById(@PathVariable("id") int id) {

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
    public List<BoardGame> getAllImgById(@RequestParam int index) throws IOException {
        List<BoardGame> list=homePageService.getAllImgById(index);
        return list;
    }

    //外部使用，搜索桌游
    @GetMapping("/search_img")
    public List<BoardGame> searchImgByValue(@RequestParam String value) throws IOException {
        List<BoardGame> list=homePageService.searchImgByValue(value);
        return list;
    }

    //外部使用，上传桌游
    @PostMapping("/post_boardGame")//上传图片，内容是二进制的图片和图片的名字
    public String postImgById(@RequestParam("file") MultipartFile file,@RequestParam("name") String name,@RequestParam("have") int have,@RequestParam("intro") String intro) throws IOException {
        homePageService.post(file,name,have,intro);
        return "ok";
    }


    //外部使用，更新桌游，包括下架，修改数量，修改名字
    @PostMapping("/update")
    public String update(@RequestBody BoardGameDto dto) throws IOException {
        homePageService.update(dto.getName(), dto.getHave(), dto.getStatus(), dto.getId(), dto.getIntro());
        return "ok";
    }

    //外部使用，更新图片
    @PostMapping("/update_img")
    public String updateImgById(@RequestParam("file") MultipartFile file,@RequestParam("id") int id) throws IOException {
        homePageService.update_img(file,id);
        return "ok";
    }

    //外部使用，注册
    @GetMapping("/register")
    public String register(@RequestParam String name,@RequestParam String username,@RequestParam String password){
        System.out.println("注册");
        return homePageService.register(name,username,password);
    }

    @GetMapping("/login")
    public UserLoginDto login(@RequestParam String username, @RequestParam String password){
        System.out.println("登录");
        return homePageService.login(username,password);
    }






    //外部使用，借桌游，最简单的版本，还没有完善
    //TODO传用户的id，来判断是否能借
    @GetMapping("/borrow")
    public String borrow(@RequestParam("id") int id) throws IOException {
        return homePageService.borrow(id);
    }

}
