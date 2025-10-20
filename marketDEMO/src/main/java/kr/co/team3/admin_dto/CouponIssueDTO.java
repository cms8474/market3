package kr.co.team3.admin_dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor @Builder
public class CouponIssueDTO {
    private String poNo;        // 발급번호 = USER_COUPON.UC_PO_NO
    private String cId;         // 쿠폰번호 = COUPON.C_ID
    private String ctType;      // 쿠폰종류 코드 = COUPON.C_CT_TYPE
    private String cName;       // 쿠폰명 = COUPON.C_NAME
    private String userId;      // 사용자(유저아이디) = USER_COUPON.UC_U_ID
    private String status;      // 발급/사용 상태 = USER_COUPON.UC_STATUS
    private LocalDateTime useDay; // 사용일 = USER_COUPON.UC_USE_DAY (TZ 이슈 없으면 LocalDateTime)
    private String issuerUid;   // ← COUPON.C_S_U_ID
    private String issuerName;
    private String cText;      // 혜택 (그대로 들고옴)

}
