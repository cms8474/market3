package kr.co.team3.product_dto;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {
    private String u_id;
    private String u_pw;
    private String u_name;
    private LocalDate u_birth;
    private String u_gender;
    private String u_mail;
    private String u_phone;
    private String u_postal;
    private String u_base_addr;
    private String u_detail_addr;
    private String u_type;
    private LocalDateTime u_create_day;
    private Integer u_point;
    private String u_rank;
    private String u_status;
    private LocalDateTime u_last_login_at;

    // 판매자 정보
    private String s_company_name;
    private String s_seller_no;
    private String s_sales_reg_num;
    private String s_tel;
    private String s_fax;
    private String s_state;
    private String s_seller_type;
}
