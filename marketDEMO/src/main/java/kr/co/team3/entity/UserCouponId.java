package kr.co.team3.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserCouponId implements Serializable {
    private String ucUId;
    private String ucCId;
}
