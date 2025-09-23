package h537.new_back_test.mapper;

import h537.new_back_test.entity.User_entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("select count(*) from borrow_test.users where username=#{username}")
    boolean findByUsername(String username);

    @Insert("insert into borrow_test.users (name, username, password) values (#{name},#{username},#{password})")
    void register(User user);

    @Select("select id from borrow_test.users where username=#{username} and password=#{password}")
    Integer login(String username, String password);

    @Update("UPDATE borrow_test.users set borrow_test.users.user_img = #{imgUrl} WHERE id = #{id}")
    void setImgUrl(int id, String imgUrl);

    @Select("select * from borrow_test.users where id=#{id}")
    User findById(int id);

    @Update("UPDATE borrow_test.users SET name = #{name}, password = #{password} WHERE id = #{id}")
    void updateUserInfoById(User user);
}
