package kr.co.team3.product_entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * 상품-태그 연결 정보 (TAGS_LINKS)
 * - PRODUCT_ITEM과 PRODUCT_TAGS를 연결하는 중간 테이블
 * - 복합키: TL_P_PID + TL_PT_NAME
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "TAGS_LINKS")
@IdClass(TagLinkId.class) // 복합키 클래스 지정
public class TagLinkEntity {

    @Id
    @Column(name = "TL_P_PID", length = 20)
    private String tlPPid; // 상품 ID (FK → PRODUCT_ITEM.P_PID)

    @Id
    @Column(name = "TL_PT_NAME", length = 20)
    private String tlPtName; // 태그명 (FK → PRODUCT_TAGS.PT_NAME)

    // 관계 설정
    @ManyToOne
    @JoinColumn(name = "TL_P_PID", referencedColumnName = "P_PID", insertable = false, updatable = false)
    private IndexEntity product;

    @ManyToOne
    @JoinColumn(name = "TL_PT_NAME", referencedColumnName = "PT_NAME", insertable = false, updatable = false)
    private ProductTagsEntity productTag;
}