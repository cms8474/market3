package kr.co.team3.product_entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * 광고 정보 (AD_INFO)
 * - 메인 상단배너 / 슬라이드 배너용
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "AD_INFO")
public class AdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "A_ID")
    private Long aId; // 광고 고유번호

    @Column(name = "A_LINK", length = 200)
    private String aLink; // 배너 클릭 시 이동 링크

    @Column(name = "A_LOCATION", length = 20)
    private String aLocation; // TOP / SLIDE 구분

    @Lob
    @Column(name = "A_FILE")
    private byte[] aFile; // 이미지 파일 (BLOB)
}
