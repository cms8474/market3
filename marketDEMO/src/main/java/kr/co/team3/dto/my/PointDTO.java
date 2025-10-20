package kr.co.team3.dto.my;

import jakarta.persistence.Column;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PointDTO {
    // 포인트 내역 조회 컬럼
    @Column(name = "uh_no")
    private String uhNo;
    @Column(name = "uh_u_id")
    private String uhUId;
    @Column(name = "uh_text")
    private String uhText;
    @Column(name = "uh_change_point")
    private String uhChangePoint;
    @Column(name = "uh_change_date")
    private String uhChangeDate;
    @Column(name = "uh_dday")
    private String uhDday;
    @Column(name = "uh_po_no")
    private String uhPoNo;
    @Column(name = "po_orderdate")
    private String poOrderdate;
    @Column(name = "po_state")
    private String poState;
}
