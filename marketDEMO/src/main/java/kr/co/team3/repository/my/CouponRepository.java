package kr.co.team3.repository.my;

import kr.co.team3.entity.my.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// 강민철 2025-10-20 1457

@Repository
public interface CouponRepository extends JpaRepository<Coupon, String> {
    int countByUcUIdAndUcStatus(String ucUId, String usStatus);
    
    /**
     * 사용자의 사용 가능한 쿠폰 목록 조회
     * @param ucUId 사용자 ID
     * @param ucStatus 쿠폰 상태
     * @return 사용 가능한 쿠폰 목록
     */
    List<Coupon> findByUcUIdAndUcStatus(String ucUId, String ucStatus);
}
