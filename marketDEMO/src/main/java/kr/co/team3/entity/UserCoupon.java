package kr.co.team3.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "USER_COUPON")
@IdClass(UserCouponId.class)
public class UserCoupon {

    @Id
    @Column(name = "UC_U_ID")
    private String ucUId;

    @Id
    @Column(name = "UC_C_ID")
    private String ucCId;

    @Column(name = "UC_PO_NO")
    private String ucPoNo;

    @Column(name = "UC_STATUS")
    private String ucStatus;

    @Column(name = "UC_USE_DAY")
    private LocalDateTime ucUseDay;
}
