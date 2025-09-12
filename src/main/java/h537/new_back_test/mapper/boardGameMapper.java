package h537.new_back_test.mapper;

import h537.new_back_test.entity.boardGame;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface boardGameMapper {
    @Select("select * from borrow_test.board_game where id=#{id}")
    boardGame getBoardGameById(int id);

    @Insert("INSERT INTO borrow_test.board_game (name) VALUES (#{boardGame.name})")
    @Options(useGeneratedKeys = true, keyProperty = "boardGame.id")
    void post(@Param("boardGame") boardGame boardGame);

    @Update("UPDATE borrow_test.board_game set board_game.img_url = #{fileName} WHERE id = #{id}")
    void setImgUrl(int id, String fileName);

    @Select("SELECT * FROM borrow_test.board_game WHERE name LIKE CONCAT('%', #{value}, '%')")
    List<boardGame> getBoardGameByValue(String value);
}
