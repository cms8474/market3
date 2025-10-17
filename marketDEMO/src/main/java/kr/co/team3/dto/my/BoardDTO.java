package kr.co.team3.dto.my;

import jakarta.persistence.Column;
import kr.co.team3.entity.my.Board;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BoardDTO {
    @Column(name = "b_id")
    private String bId;
    @Column(name = "b_u_id")
    private String bUId;
    @Column(name = "b_title")
    private String bTitle;
    @Column(name = "b_body")
    private String bBody;
    @Column(name = "b_state")
    private String bState;
    @Column(name = "b_listener")
    private String bListener;  // 문의 채널
    @Column(name = "b_answer")
    private String bAnswer;
    @Column(name = "b_view")
    private String bView;
    @Column(name = "b_file")
    private String bFile;
    @Column(name = "b_reg_date")
    private String bRegDate;

    private BoardTypeDTO boardTypeDTO;

    public Board ToEntity() {
        return Board.builder()
                .bId(bId)
                .bUId(bUId)
                .bTitle(bTitle)
                .bBody(bBody)
                .bState(bState)
                .bListener(bListener)
                .bAnswer(bAnswer)
                .bView(bView)
                .bFile(bFile)
                .boardType(boardTypeDTO.ToEntity())
                .build();
    }
}
