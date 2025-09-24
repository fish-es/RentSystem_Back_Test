package h537.new_back_test.entity.GameOrder_entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardGameApplication {
    /** 申请人uID */
    private int uid;

    /** 所借桌游ID */
    private int gameId;

    /** 申请数量 */
    private int quantity;

    /** 申请人姓名 */
    private String applicantName;

    /** 申请人电话 */
    private String applicantPhone;

    /** 申请时的备注 */
    private String applyRemark;

    /** 预计取货时间 */
    private LocalDate expectedPickup;

    /** 预计归还时间 */
    private LocalDate expectedReturn;
}
