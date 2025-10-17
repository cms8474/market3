package kr.co.team3.entity.my;

import jakarta.persistence.*;
import kr.co.team3.dto.my.BoardDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "BOARD")
public class Board {
    @Id
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
    private String bListener;
    @Column(name = "b_answer")
    private String bAnswer;
    @Column(name = "b_view")
    private String bView;
    @Column(name = "b_file")
    private String bFile;
    @Column(name = "b_reg_date")
    private LocalDateTime bRegDate;

    @ManyToOne
    @JoinColumn(name = "b_bt_type", referencedColumnName = "bt_type")
    private MyBoardType boardType;

    public BoardDTO ToDTO() {
        return BoardDTO.builder()
                .bId(bId)
                .bUId(bUId)
                .bTitle(bTitle)
                .bBody(bBody)
                .bState(bState)
                .bListener(bListener)
                .bAnswer(bAnswer)
                .bView(bView)
                .bFile(bFile)
                .bRegDate(bRegDate.toString())
                .boardTypeDTO(boardType.ToDTO())
                .build();
    }
}
