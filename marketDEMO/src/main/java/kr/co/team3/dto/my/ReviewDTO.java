package kr.co.team3.dto.my;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ReviewDTO {
    private String pr_no;
    private String pr_po_no;
    private String pr_p_pid;
    private String pr_u_id;
    private int pr_star;
    private String pr_body;
    private String pr_reg_date;
    private String pr_images1;
    private String pr_images2;
    private String pr_images3;
}
