package kr.co.team3.admin_dto;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor @Builder
public class CouponDTO {
    private String cId;          // 쿠폰번호
    private String ctType;       // 쿠폰종류 코드

    private String cName;        // 쿠폰명
    private String benefit;      // 혜택(가공: C_TEXT 우선, 없으면 DIS_* 결합)
    private String cDisDelivery; // 배송비 할인 (무료 또는 정수)
    private String cDisMoney;    // 금액 할인 (정수 또는 %)

    private LocalDate startDay;  // 사용기간 시작
    private LocalDate endDay;    // 사용기간 종료

    private String issuerUid;    // 발급자 ID (COUPON.C_S_U_ID)
    private String issuerName;   // 발급자 상호명 (SELLER_INFO.S_COMPANY_NAME)

    private Integer amount;      // 발급수
    private Integer used;        // 사용수
    private String status;       // 상태

    private LocalDateTime regDate; // 발급일(테이블 없음 → null; 선택: 컬럼 추가 시 매핑)
}
