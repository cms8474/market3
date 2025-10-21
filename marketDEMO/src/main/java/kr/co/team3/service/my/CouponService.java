package kr.co.team3.service.my;

import kr.co.team3.entity.my.Coupon;
import kr.co.team3.repository.my.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// 강민철 2025-10-20 1457

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;

    public int getNumberofCouponsWithUcUIdAndStatus(String ucUId, String usStatus) {
        return couponRepository.countByUcUIdAndUcStatus(ucUId,usStatus);
    }
    
    /**
     * 사용자의 사용 가능한 쿠폰 목록 조회
     * @param ucUId 사용자 ID
     * @return 사용 가능한 쿠폰 목록
     */
    public List<Coupon> getAvailableCouponsByUserId(String ucUId) {
        return couponRepository.findByUcUIdAndUcStatus(ucUId, "사용가능");
    }
}
