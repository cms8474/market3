package kr.co.team3.product_entity;

import lombok.*;

import java.io.Serializable;

/**
 * TAGS_LINKS 테이블의 복합키 클래스
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TagLinkId implements Serializable {

    private String tlPPid; // 상품 ID
    private String tlPtName; // 태그명
}