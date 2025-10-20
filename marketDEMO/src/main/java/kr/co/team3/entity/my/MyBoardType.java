package kr.co.team3.entity.my;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.co.team3.dto.my.BoardTypeDTO;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "BOARD_TYPE")
public class MyBoardType {
    @Id
    @Column(name = "bt_type")
    private String btType;
    @Column(name = "bt_name")
    private String btName;

    public BoardTypeDTO ToDTO(){
        return BoardTypeDTO.builder()
                .btType(btType)
                .btName(btName)
                .build();
    }
}
