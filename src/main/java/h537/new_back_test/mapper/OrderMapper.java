package h537.new_back_test.mapper;

import h537.new_back_test.entity.GameOrder_entity.BoardGameApplication;
import h537.new_back_test.entity.GameOrder_entity.BoardGameOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    @Insert("INSERT INTO borrow_test.boardgame_order (applicant_id,game_id,quantity,applicant_name,applicant_phone,apply_time,expected_pickup,expected_return,status,apply_remark,reject_reason) VALUES (#{applicantId},#{gameId},#{quantity},#{applicantName},#{applicantPhone},#{applyTime},#{expectedPickup},#{expectedReturn},#{status},#{applyRemark},#{rejectReason})")
    void applications(BoardGameOrder boardGameOrder);
}
