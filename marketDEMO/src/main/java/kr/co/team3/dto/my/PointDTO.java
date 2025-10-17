package kr.co.team3.dto.my;

import jakarta.persistence.Column;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PointDTO {
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
    private String uhPONo;
}
