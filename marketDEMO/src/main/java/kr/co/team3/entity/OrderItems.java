package kr.co.team3.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ORDER_ITEMS")
@IdClass(OrderItemsId.class)
public class OrderItems {

    @Id
    @Column(name = "OI_PO_NO")
    private String oiPoNo;

    @Id
    @Column(name = "OI_P_PID")
    private String oiPPid;

    @Column(name = "OI_S_U_ID")
    private String oiSUId;

    @Column(name = "OI_PROD_QTY")
    private Integer oiProdQty;

    @Column(name = "OI_TOT_PRICE")
    private Integer oiTotPrice;

    @Column(name = "OI_TRACKING_COMPANY")
    private String oiTrackingCompany;

    @Column(name = "OI_TRACKING_NUM")
    private String oiTrackingNum;

    @Column(name = "OI_ETC")
    private String oiEtc;

    @Column(name = "OI_DEL_STATUS")
    private String oiDelStatus;
}
