package h537.new_back_test.service;

import h537.new_back_test.entity.GameOrder_entity.BoardGameApplication;
import h537.new_back_test.entity.GameOrder_entity.BoardGameOrder;
import h537.new_back_test.mapper.BoardGameMapper;
import h537.new_back_test.mapper.OrderMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class OrderService {

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    BoardGameMapper boardGameMapper;

    @Transactional
    public String applications(BoardGameApplication boardGameApplication) {
        //先判断库存够不够或者是否已经下架
        int have=boardGameMapper.getHaveById(boardGameApplication.getGameId());
        if(have==0){
            return "商品库存为零";
        }
        int status=boardGameMapper.getStatusById(boardGameApplication.getGameId());
        if(status==1){
            return "商品已经被下架";
        }

        int id = boardGameApplication.getUid() - 10000;
        BoardGameOrder boardGameOrder = new BoardGameOrder();
        BeanUtils.copyProperties(boardGameApplication, boardGameOrder);
        boardGameOrder.setApplicantId(id);
        boardGameOrder.setApplyTime(LocalDateTime.now());
        boardGameOrder.setStatus(0);
        //提交订单
        orderMapper.applications(boardGameOrder);
        //库存减一
        boardGameMapper.borrow(boardGameApplication.getGameId());
        return "success";
    }

}
