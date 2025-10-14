package kr.co.team3.product_entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "U_USER") // ✅ DBeaver에 실제 있는 테이블명 그대로
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberEntity {

    @Id
    @Column(name = "U_ID")
    private String uId;

    @Column(name = "U_PW")
    private String uPw;

    @Column(name = "U_NAME")
    private String uName;

    @Column(name = "U_BIRTH")
    @Temporal(TemporalType.DATE)
    private Date uBirth;

    @Column(name = "U_GENDER")
    private String uGender;

    @Column(name = "U_MAIL")
    private String uMail;

    @Column(name = "U_PHONE")
    private String uPhone;

    @Column(name = "U_POSTAL")
    private String uPostal;

    @Column(name = "U_BASE_ADDR")
    private String uBaseAddr;

    @Column(name = "U_DETAIL_ADDR")
    private String uDetailAddr;

    @Column(name = "U_TYPE")
    private String uType;

    @Column(name = "U_CREATE_DAY")
    private LocalDateTime uCreateDay;

    @Column(name = "U_POINT")
    private Integer uPoint;

    @Column(name = "U_RANK")
    private String uRank;

    @Column(name = "U_STATUS")
    private String uStatus;

    @Column(name = "U_LAST_LOGIN_AT")
    private LocalDateTime uLastLoginAt;
}
