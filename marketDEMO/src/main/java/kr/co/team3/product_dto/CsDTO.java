package kr.co.team3.product_dto;

import kr.co.team3.product_entity.CsEntity;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CsDTO {

    private String boardId;
    private String boardWriter;
    private String boardType;
    private String boardTitle;
    private String boardContent;
    private String boardState;
    private String boardListener;
    private String boardAnswer;
    private Integer boardView;
    private byte[] boardFile;
    private LocalDateTime boardRegDate;

    public static CsDTO fromEntity(CsEntity e) {
        if (e == null) return null;
        return CsDTO.builder()
                .boardId(e.getBoardId())
                .boardWriter(e.getBoardWriter())
                .boardType(e.getBoardType())
                .boardTitle(e.getBoardTitle())
                .boardContent(e.getBoardContent())
                .boardState(e.getBoardState())
                .boardListener(e.getBoardListener())
                .boardAnswer(e.getBoardAnswer())
                .boardView(e.getBoardView())
                .boardFile(e.getBoardFile())
                .boardRegDate(e.getBoardRegDate())
                .build();
    }

    public CsEntity toEntity() {
        return CsEntity.builder()
                .boardId(boardId)
                .boardWriter(boardWriter)
                .boardType(boardType)
                .boardTitle(boardTitle)
                .boardContent(boardContent)
                .boardState(boardState)
                .boardListener(boardListener)
                .boardAnswer(boardAnswer)
                .boardView(boardView)
                .boardFile(boardFile)
                .boardRegDate(boardRegDate)
                .build();
    }
}
