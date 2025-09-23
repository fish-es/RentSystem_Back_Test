package h537.new_back_test.service;

import h537.new_back_test.entity.Game_entity.BoardGame;
import h537.new_back_test.mapper.BoardGameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardGameService {
    @Autowired
    BoardGameMapper boardGameMapper;


    public List<BoardGame> getAllImgById(int index) {

        List<BoardGame> list= new ArrayList<>();
        for(int i=index*10+1;i<=index*10+10;i++){
            BoardGame game = boardGameMapper.getBoardGameById(i);

            list.add(game);
        }

        return list;
    }

    public void postImgById(MultipartFile file, String name, int have, String intro) throws IOException {
        // 调用 Mapper 方法插入数据并获取主键
        BoardGame boardGame = new BoardGame();
        boardGame.setName(name);
        boardGame.setHave(have);
        boardGame.setIntro(intro);
        boardGameMapper.postImgById(boardGame);
        int id = boardGame.getId();

        // 指定存储路径
        String uploadPath = "/img"; // 示例路径，请根据实际情况修改
        // 指定文件名，所有图片都使用 .jpg 后缀
        String fileName = id + ".jpg";

        String imgUrl="https://h537.xyz/api/get_img/"+id;

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

    public List<BoardGame> searchImgByValue(String value) {
        List<BoardGame> list = boardGameMapper.getBoardGameByValue(value);
        return list;
    }

    public String borrow(int id) {
        BoardGame game = boardGameMapper.getBoardGameById(id);
        if(game.getStatus()==1){
            return "商品下架中，无法借";
        }
        if(game.getHave()<=0){
            return "没有库存了，无法借";
        }
        boardGameMapper.borrow(id);
        return "ok";
    }

    public void update(String name, int have, int status, int id,String intro) throws IOException {
        BoardGame boardGame =BoardGame.builder().id(id).name(name).have(have).status(status).intro(intro).build();

        boardGameMapper.update(boardGame);

    }

    public void updateImgById(MultipartFile file, int id) throws IOException {
        // 指定存储路径
        String uploadPath = "/img"; // 示例路径，请根据实际情况修改
        // 指定文件名，所有图片都使用 .jpg 后缀
        String fileName = id + ".jpg";

        String imgUrl="https://h537.xyz/api/get_img/"+id;
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
