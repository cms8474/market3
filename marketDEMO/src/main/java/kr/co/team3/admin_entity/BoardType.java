package kr.co.team3.admin_entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "BOARD_TYPE") // 실제 테이블명 맞게 변경
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardType {

    @Id
    @Column(name = "BT_TYPE")   // faq11, noti01 ...
    private String type;

    @Column(name = "BT_NAME")   // 가입, 포인트, 고객서비스 ...
    private String name;
}