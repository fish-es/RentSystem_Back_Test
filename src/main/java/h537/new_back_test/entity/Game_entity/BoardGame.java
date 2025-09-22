package h537.new_back_test.entity.Game_entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardGame {
    int id;
    String name;
    String imgUrl;
    int have;
    int status;
    String intro;
}
