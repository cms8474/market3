package kr.co.team3.product_entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "BOARD")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CsEntity {

    @Id
    @Column(name = "B_ID")
    private String boardId; // PK (VARCHAR2(20))

    @Column(name = "B_U_ID")
    private String boardWriter; // 작성자

    @Column(name = "B_BT_TYPE")
    private String boardType; // NOTICE / FAQ / QNA

    @Column(name = "B_TITLE")
    private String boardTitle; // 제목

    @Column(name = "B_BODY")
    private String boardContent; // 본문

    @Column(name = "B_STATE")
    private String boardState; // 상태 (검토 중 / 답변 완료)

    @Column(name = "B_LISTENER")
    private String boardListener; // 담당자

    @Column(name = "B_ANSWER")
    private String boardAnswer; // 답변

    @Column(name = "B_VIEW")
    private Integer boardView; // 조회수

    @Lob
    @Column(name = "B_FILE")
    private byte[] boardFile; // 첨부파일

    @Column(name = "B_REG_DATE")
    private LocalDateTime boardRegDate; // 등록일시

    /* =========================
       계산용 파생 속성 (저장 안 함)
       ========================= */

    /** ex) faq11_admin01_0001 → faq11  */
    @Transient
    public String getTypeCode() {
        if (boardId == null) return "";
        String full = boardId.trim().toLowerCase();
        int idx = full.indexOf('_');
        return (idx > 0) ? full.substring(0, idx) : full;
    }

    /** ex) faq11 → faq10, faq2 → faq20 (마지막 숫자를 0으로) */
    @Transient
    public String getLv1Code() {
        String code = getTypeCode();
        if (code.isEmpty()) return "";
        char last = code.charAt(code.length() - 1);
        if (Character.isDigit(last)) {
            return code.substring(0, code.length() - 1) + "0";
        }
        return code; // 숫자로 끝나지 않으면 원본 유지
    }
}
