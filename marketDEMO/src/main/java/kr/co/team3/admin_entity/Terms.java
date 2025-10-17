package kr.co.team3.admin_entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="M_TERMS")
public class Terms {
    @Id
    @Column(name = "T_NAME", length = 50, nullable = false)
    private String tName;

    @Lob
    @Column(name = "T_BODY")
    private String tBody;
}
