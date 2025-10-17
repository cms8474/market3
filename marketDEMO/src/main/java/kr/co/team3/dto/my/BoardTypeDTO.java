package kr.co.team3.dto.my;

import jakarta.persistence.Column;
import kr.co.team3.entity.my.MyBoardType;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BoardTypeDTO {
    @Column(name = "bt_type")
    private String btType;
    @Column(name = "bt_name")
    private String btName;

    public MyBoardType ToEntity(){
        return MyBoardType.builder()
                .btType(btType)
                .btName(btName)
                .build();
    }
}
