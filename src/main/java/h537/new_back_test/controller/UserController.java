package h537.new_back_test.controller;

import h537.new_back_test.entity.User_entity.User;
import h537.new_back_test.entity.User_entity.UserDto;
import h537.new_back_test.entity.User_entity.UserLoginDto;
import h537.new_back_test.service.UserService;
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

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

    //内部使用，获得用户的头像
    @GetMapping("/get_user_img/{id}")
    public ResponseEntity<byte[]> userImgById(@PathVariable("id") int id) {
        String uploadPath = "/img/user";
        String defaultPath = "/img/user/0.jpg";   // 默认图
        String fileName = id + ".jpg";

        File file = new File(uploadPath, fileName);
        // 如果用户图不存在，就用默认图
        if (!file.exists()) {
            file = new File(defaultPath);
            // 如果连默认图也没有，再 404
            if (!file.exists()) {
                return ResponseEntity.notFound().build();
            }
        }

        try {
            byte[] imageBytes = Files.readAllBytes(file.toPath());

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

    //外部使用，注册
    @GetMapping("/register")
    public String register(@RequestParam String name, @RequestParam String username, @RequestParam String password){
        return userService.register(name,username,password);
    }
    //外部使用，登录
    @GetMapping("/login")
    public UserLoginDto login(@RequestParam String username, @RequestParam String password){
        return userService.login(username,password);
    }

    //外部使用，通过uid获得用户的信息
    @GetMapping("/get_user_info")
    public User getUserInfo(@RequestParam int uid){
        int id=uid-10000;
        return userService.getUserInfo(id);
    }

    //外部使用，修改个人的头像
    @PostMapping("/update_user_img")
    public String updateUserImgById(@RequestParam("file") MultipartFile file, @RequestParam("uid") int uid) throws IOException {
        int id=uid-10000;
        userService.updateUserImg(file,id);
        return "success";
    }

    //外部使用，修改个人的详细信息
    @PostMapping("/update_user_info")
    public String updateUserInfoById(@RequestBody UserDto userDto){
        userDto.setUid(userDto.getUid()-10000);
        userService.updateUserInfoById(userDto.getUid(),userDto.getName(),userDto.getPassword());
        return "success";
    }


}
