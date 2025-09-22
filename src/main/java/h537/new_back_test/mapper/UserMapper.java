package h537.new_back_test.mapper;

import h537.new_back_test.entity.User_entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select count(*) from users where username=#{username}")
    boolean findByUsername(String username);

    @Insert("insert into users (name, username, password) values (#{name},#{username},#{password})")
    void register(User user);

    @Select("select id from users where username=#{username} and password=#{password}")
    Integer login(String username, String password);
}
