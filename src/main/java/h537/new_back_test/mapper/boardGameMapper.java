package h537.new_back_test.mapper;

import h537.new_back_test.entity.boardGame;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface boardGameMapper {
    @Select("select * from borrow_test.board_game where id=#{id}")
    boardGame getBoardGameById(int id);

    @Insert("INSERT INTO borrow_test.board_game (name) VALUES (#{name})")
    void post(String name);
}
