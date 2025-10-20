package kr.co.team3.dto.my;

import jakarta.persistence.Column;
import kr.co.team3.entity.my.Coupon;
import lombok.*;

// 강민철 2025-10-20 1457

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CouponDTO {
    @Column(name = "uc_u_id")
    private String ucUId;
    @Column(name = "uc_c_id")
    private String ucCId;
    @Column(name = "uc_po_no")
    private String ucPoNo;
    @Column(name = "uc_status")
    private String ucStatus;
    @Column(name = "uc_use_day")
    private String ucUseDay;

    public Coupon toEntity() {
        return Coupon.builder()
                .ucUId(ucUId)
                .ucCId(ucCId)
                .ucPoNo(ucPoNo)
                .ucStatus(ucStatus)
                .build();
    }
}
