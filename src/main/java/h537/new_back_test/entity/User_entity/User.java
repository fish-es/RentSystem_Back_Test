package h537.new_back_test.entity.User_entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    int id;
    String name;
    String username;
    String password;
    String imgUrl;
}
