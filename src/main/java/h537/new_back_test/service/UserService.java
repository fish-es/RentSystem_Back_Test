package h537.new_back_test.service;

import h537.new_back_test.entity.User_entity.User;
import h537.new_back_test.entity.User_entity.UserLoginDto;
import h537.new_back_test.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public String register(String name, String username, String password) {
        boolean num=userMapper.findByUsername(username);
        if(num){
            return "fail";
        }
        User user= User.builder().name(name).username(username).password(password).build();

        userMapper.register(user);
        return "success";
    }

    public UserLoginDto login(String username, String password) {
        Integer uid= userMapper.login(username,password);

        if (uid!=null){
            return UserLoginDto.builder().uid(10000+uid).status("success").build();
        }
        else {
            return UserLoginDto.builder().uid(-1).status("fail").build();
        }
    }

    public void updateUserImg(MultipartFile file, int id) throws IOException {
        String uploadPath = "/img/user"; // 示例路径，请根据实际情况修改
        // 指定文件名，所有图片都使用 .jpg 后缀
        String fileName = id + ".jpg";

        String imgUrl="https://h537.xyz/api/get_user_img/"+id;
        userMapper.setImgUrl(id,imgUrl);
        // 创建目标文件对象
        File destFile = new File(uploadPath, fileName);
        // 确保目录存在
        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }
        // 将文件保存到服务器
        file.transferTo(destFile);
    }

    public User getUserInfo(int id) {
        return userMapper.findById(id);
    }

    public void updateUserInfoById(int id, String name, String password) {
        User user = User.builder().id(id).name(name).password(password).build();
        userMapper.updateUserInfoById(user);
    }
}
