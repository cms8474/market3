package kr.co.team3.repository;

import kr.co.team3.entity.UserCoupon;
import kr.co.team3.entity.UserCouponId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCouponRepository extends JpaRepository<UserCoupon, UserCouponId> {
}
