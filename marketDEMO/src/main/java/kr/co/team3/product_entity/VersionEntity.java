package kr.co.team3.product_entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * 회사 정보 / 버전 정보 (VERSION)
 * - Footer 표시용
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "VERSION")
public class VersionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "V_ID")
    private Long vId;

    @Column(name = "V_COMPANY", length = 100)
    private String vCompany; // 회사명

    @Column(name = "V_EMAIL", length = 100)
    private String vEmail; // 이메일

    @Column(name = "V_ADDR", length = 200)
    private String vAddr; // 주소

    @Column(name = "V_TEL", length = 20)
    private String vTel; // 연락처
}
