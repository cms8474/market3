package kr.co.team3.admin_service;

import kr.co.team3.admin_dto.CouponDTO;
import kr.co.team3.admin_dto.PageRequestDTO;
import kr.co.team3.admin_dto.PageResponseDTO;
import kr.co.team3.admin_mapper.AdminCouponMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCouponService {

    private final AdminCouponMapper admincouponMapper;


    /** 쿠폰 목록 + 전체 개수 조회 후, 네 PageResponseDTO로 감싸서 반환 */
    public PageResponseDTO<CouponDTO> getCouponPage(PageRequestDTO req) {
        List<CouponDTO> list = admincouponMapper.selectCouponList(req);
        int total = admincouponMapper.countCouponList(req);
        return new PageResponseDTO<>(req, list, total);
    }
}
