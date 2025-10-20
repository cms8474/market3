package kr.co.team3.repository.my;

import kr.co.team3.entity.my.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// 강민철 2025-10-20 1457

@Repository
public interface CouponRepository extends JpaRepository<Coupon, String> {
    int countByUcUIdAndUcStatus(String ucUId, String usStatus);
}
