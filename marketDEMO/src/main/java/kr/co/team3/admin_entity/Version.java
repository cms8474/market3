package kr.co.team3.admin_entity;

import jakarta.persistence.*;
import kr.co.team3.admin_dto.VersionDTO;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "version")
@SequenceGenerator(
        name = "version_seq",
        sequenceName = "VERSION_V_NO_SEQ", // DB에 있는 시퀀스 이름
        allocationSize = 1
)
public class Version {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "version_seq")
    @Column(name = "v_no")
    private Long id;

    @Column(name = "v_vname")
    private String vname;

    @Column(name = "v_uploader")
    private String uploader;

    @Column(name = "v_reg_date", insertable = false, updatable = false)
    private LocalDateTime regdate;

    @Column(name = "v_body")
    private String body;

    @Column(name = "v_title")
    private String title;

    @Column(name = "v_sub_title")
    private String subTitle;

    @Column(name = "v_header_logo")
    private String headerLogo;

    @Column(name = "v_footer_logo")
    private String footerLogo;

    @Column(name = "v_favicon")
    private String favicon;

    @Column(name = "v_company")
    private String company;

    @Column(name = "v_ceo")
    private String ceo;

    @Column(name = "v_seller_reg_no")
    private String sellerRegNo;

    @Column(name = "v_online_sales_reg_no")
    private String onlineSalesRegNo;

    @Column(name = "v_addr")
    private String addr;

    @Column(name = "v_tel")
    private String tel;

    @Column(name = "v_act_time")
    private String actTime;

    @Column(name = "v_email")
    private String email;

    @Column(name = "v_manager_phone")
    private String managerPhone;

    @Column(name = "v_copylight")
    private String copylight;

    // 편의 복제 메서드(최신값 복사 후 일부 필드만 덮어쓰기용)
    public Version copy() {
        return Version.builder()
                .vname(vname).uploader(uploader).body(body).title(title).subTitle(subTitle)
                .headerLogo(headerLogo).footerLogo(footerLogo).favicon(favicon)
                .company(company).ceo(ceo).sellerRegNo(sellerRegNo).onlineSalesRegNo(onlineSalesRegNo)
                .addr(addr).tel(tel).actTime(actTime).email(email).managerPhone(managerPhone)
                .copylight(copylight)
                .build();
    }

    // 강민철 2025-10-21 1214 수정
    public VersionDTO toDTO(){
        return VersionDTO.builder()
                .vname(vname).uploader(uploader).body(body).title(title).subTitle(subTitle)
                .headerLogo(headerLogo).footerLogo(footerLogo).favicon(favicon)
                .company(company).ceo(ceo).sellerRegNo(sellerRegNo).onlineSalesRegNo(onlineSalesRegNo)
                .addr(addr).tel(tel).actTime(actTime).email(email).managerPhone(managerPhone)
                .copylight(copylight)
                .build();
    }
}