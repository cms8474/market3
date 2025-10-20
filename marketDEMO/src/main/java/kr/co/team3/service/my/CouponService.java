package kr.co.team3.service.my;

import kr.co.team3.repository.my.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// 강민철 2025-10-20 1457

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;

    public int getNumberofCouponsWithUcUIdAndStatus(String ucUId, String usStatus) {
        return couponRepository.countByUcUIdAndUcStatus(ucUId,usStatus);
    }
}
