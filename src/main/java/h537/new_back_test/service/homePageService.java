package h537.new_back_test.service;

import h537.new_back_test.entity.boardGame;
import h537.new_back_test.entity.uploadGame;
import h537.new_back_test.mapper.boardGameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class homePageService {

    @Autowired
    boardGameMapper boardGameMapper;


    public List<boardGame> getAllImgById(int index) {

        List<boardGame> list= new ArrayList<>();
        for(int i=index*10+1;i<=index*10+10;i++){
            boardGame game = boardGameMapper.getBoardGameById(i);

            list.add(game);
        }

        return list;
    }

    public void post(MultipartFile file,String name) throws IOException {
        System.out.println("进入server");
        // 调用 Mapper 方法插入数据并获取主键
        boardGame boardGame = new boardGame();
        boardGame.setName(name);
        boardGameMapper.post(boardGame);
        int id = boardGame.getId();

        // 指定存储路径
        String uploadPath = "/img"; // 示例路径，请根据实际情况修改
        // 指定文件名，所有图片都使用 .jpg 后缀
        String fileName = id + ".jpg";

        String imgUrl="h537.xyz:8080/get_img/"+id;
        boardGameMapper.setImgUrl(id,imgUrl);
        // 创建目标文件对象
        File destFile = new File(uploadPath, fileName);
        // 确保目录存在
        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }
        // 将文件保存到服务器
        file.transferTo(destFile);
    }
}
