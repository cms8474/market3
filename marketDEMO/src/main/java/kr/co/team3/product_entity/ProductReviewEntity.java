package kr.co.team3.product_entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * PRODUCT_REVIEW 테이블 기반 상품 리뷰 Entity
 */
@Entity
@Table(name = "PRODUCT_REVIEW")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductReviewEntity {

    @Id
    @Column(name = "PR_NO", length = 100)
    private String prNo; // 리뷰 번호 (PK)

    @Column(name = "PR_PO_NO", length = 50)
    private String prPoNo; // 주문 번호

    @Column(name = "PR_P_PID", length = 50)
    private String prPPid; // 상품 ID (FK → PRODUCT_ITEM.P_PID)

    @Column(name = "PR_U_ID", length = 20)
    private String prUId; // 사용자 ID (FK → MEMBER.M_ID)

    @Column(name = "PR_STAR")
    private Double prStar; // 별점 (1.0 ~ 5.0)

    @Column(name = "PR_BODY", length = 500)
    private String prBody; // 리뷰 내용

    @Column(name = "PR_REG_DATE")
    private LocalDateTime prRegDate; // 리뷰 등록일

    @Column(name = "PR_IMAGES1", length = 100)
    private String prImages1; // 리뷰 이미지1

    @Column(name = "PR_IMAGES2", length = 100)
    private String prImages2; // 리뷰 이미지2

    @Column(name = "PR_IMAGES3", length = 100)
    private String prImages3; // 리뷰 이미지3
}
