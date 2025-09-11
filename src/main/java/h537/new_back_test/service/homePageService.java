package h537.new_back_test.service;

import h537.new_back_test.entity.boardGame;
import h537.new_back_test.entity.uploadGame;
import h537.new_back_test.mapper.boardGameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public void post(String name) throws IOException {
        System.out.println("进入server");
        boardGameMapper.post(name);

    }
}
