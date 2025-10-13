package kr.co.team3.product_entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "SELLER_INFO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellerInfoEntity {

    @Id
    @Column(name = "S_U_ID", length = 20)
    private String sUId;  // FK → U_USER.U_ID

    @OneToOne
    @JoinColumn(name = "S_U_ID", referencedColumnName = "U_ID", insertable = false, updatable = false)
    private MemberEntity member;

    @Column(name = "S_COMPANY_NAME", length = 20)
    private String sCompanyName;

    @Column(name = "S_SELLER_NO", length = 50)
    private String sSellerNo;

    @Column(name = "S_SALES_REG_NUM", length = 50)
    private String sSalesRegNum;

    @Column(name = "S_TEL", length = 20)
    private String sTel;

    @Column(name = "S_FAX", length = 20)
    private String sFax;

    @Column(name = "S_STATE", length = 20)
    private String sState;

    @Column(name = "S_SELLER_TYPE", length = 20)
    private String sSellerType;
}
