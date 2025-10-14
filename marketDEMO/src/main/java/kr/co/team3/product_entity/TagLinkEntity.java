package kr.co.team3.product_entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * 상품-태그 연결 정보 (TAG_LINKS)
 * - 예: '추천', '인기', '세일' 등 태그 관리
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "TAG_LINKS")
public class TagLinkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TL_ID")
    private Long tlId; // 기본키

    @Column(name = "TL_P_PID", length = 20)
    private String tlPPid; // 상품 ID (PRODUCT_ITEM.P_PID)

    @Column(name = "TL_PT_NAME", length = 20)
    private String tlPtName; // 태그명 (예: 추천, 세일 등)
}
