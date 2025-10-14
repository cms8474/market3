package kr.co.team3.admin_entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "version")
public class Version {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "v_no")
    private Long id;

    @Column(name = "v_vname")
    private String vname;

    @Column(name = "v_uploader")
    private String uploader;

    @Column(name = "v_body")
    private String body;

    @Column(name = "v_title")
    private String title;

    @Column(name = "v_sub_title")
    private String subTitle;

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

}