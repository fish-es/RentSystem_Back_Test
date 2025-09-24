package h537.new_back_test.mapper;

import h537.new_back_test.entity.Game_entity.BoardGame;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardGameMapper {
    @Select("select * from borrow_test.board_game where id=#{id}")
    BoardGame getBoardGameById(int id);

    @Insert("INSERT INTO borrow_test.board_game (name,have,intro) VALUES (#{boardGame.name},#{boardGame.have},#{boardGame.intro})")
    @Options(useGeneratedKeys = true, keyProperty = "boardGame.id")
    void postImgById(@Param("boardGame") BoardGame boardGame);

    @Update("UPDATE borrow_test.board_game set board_game.img_url = #{fileName} WHERE id = #{id}")
    void setImgUrl(int id, String fileName);

    @Select("SELECT * FROM borrow_test.board_game WHERE name LIKE CONCAT('%', #{value}, '%')")
    List<BoardGame> getBoardGameByValue(String value);

    @Update("UPDATE borrow_test.board_game set board_game.have =board_game.have-1  WHERE id = #{id}")
    void borrow(int id);

    @Select("select have from borrow_test.board_game where id=#{id}")
    int getHaveById(int id);

    void update(BoardGame boardGame);

    @Select("select status from borrow_test.board_game where id=#{id}")
    int getStatusById(int gameId);
}
