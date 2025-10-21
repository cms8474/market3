package kr.co.team3.product_entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * 상품 태그 마스터 테이블 (PRODUCT_TAGS)
 * - 태그 정보 관리
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "PRODUCT_TAGS")
public class ProductTagsEntity {

    @Id
    @Column(name = "PT_NAME", length = 20)
    private String ptName; // 태그명 (PK) - '히트', '추천', '최신', '인기', '할인' 등

    @Column(name = "PT_DESCRIPTION", length = 100)
    private String ptDescription; // 태그 설명
}
