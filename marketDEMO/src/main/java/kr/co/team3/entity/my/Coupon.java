package kr.co.team3.entity.my;

import jakarta.persistence.*;
import kr.co.team3.dto.my.CouponDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

// 강민철 2025-10-20 1457

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "USER_COUPON")
public class Coupon {
    @Id
    @Column(name = "uc_u_id")
    private String ucUId;
    @Column(name = "uc_c_id")
    private String ucCId;
    @Column(name = "uc_po_no")
    private String ucPoNo;
    @Column(name = "uc_status")
    private String ucStatus;

    @Column(name = "uc_use_day")
    @CreationTimestamp
    private LocalDateTime ucUseDay;

    public CouponDTO toDTO() {
        return CouponDTO.builder()
                .ucUId(ucUId)
                .ucCId(ucCId)
                .ucPoNo(ucPoNo)
                .ucStatus(ucStatus)
                .build();
    }
}
