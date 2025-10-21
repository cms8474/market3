package kr.co.team3.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderItemsId implements Serializable {
    private String oiPoNo;
    private String oiPPid;
}
