package h537.new_back_test.entity.GameOrder_entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardGameOrder {
    /** 主键 */
    private Long id;

    /** 申请人ID */
    private Long applicantId;

    /** 所借桌游ID */
    private Long gameId;

    /** 申请数量 */
    private Integer quantity;

    /** 申请人姓名 */
    private String applicantName;

    /** 申请人电话 */
    private String applicantPhone;

    /** 申请时间 */
    private LocalDateTime applyTime;

    /** 预计取货时间 */
    private LocalDateTime expectedPickup;

    /** 预计归还时间 */
    private LocalDateTime expectedReturn;

    /** 订单状态：0-待审核 1-已通过 2-已取货 3-已归还 4-已取消 */
    private Integer status;

    /** 申请时的备注 */
    private String applyRemark;

    /** 拒绝理由 */
    private String rejectReason;

}
