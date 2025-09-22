package h537.new_back_test.entity.Game_entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardGameDto {
    private int id;
    private String name;
    private int have;
    private String intro;
    private int status;
}