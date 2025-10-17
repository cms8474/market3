package kr.co.team3.dto.my;

import jakarta.persistence.Column;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ReviewDTO {

    @Column(name = "pr_no")
    private String prNo;

    @Column(name = "pr_po_no")
    private String prPoNo;

    @Column(name = "pr_p_pid")
    private String prPPid;

    @Column(name = "pr_u_id")
    private String prUId;

    @Column(name = "pr_star")
    private int prStar;

    @Column(name = "pr_body")
    private String prBody;

    @Column(name = "pr_reg_date")
    private String prRegDate;

    @Column(name = "pr_images1")
    private String prImages1;

    @Column(name = "pr_images2")
    private String prImages2;

    @Column(name = "pr_images3")
    private String prImages3;

    // 추가 컬럼
    @Column(name = "p_name")
    private String pName;
}
