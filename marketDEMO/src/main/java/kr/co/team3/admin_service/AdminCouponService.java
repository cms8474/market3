package kr.co.team3.admin_service;

import kr.co.team3.admin_dto.CouponDTO;
import kr.co.team3.admin_dto.PageRequestDTO;
import kr.co.team3.admin_dto.PageResponseDTO;
import kr.co.team3.admin_mapper.AdminCouponMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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
    /**쿠폰 등록*/
    public void registerCoupon(CouponDTO dto) {
        log.info(">>> [Service] 쿠폰 등록 요청: {}", dto);
        int result = admincouponMapper.insertCoupon(dto);

        if (result > 0) {
            log.info(">>> 쿠폰 등록 완료 (쿠폰명: {}, 발급처: {})", dto.getCName(), dto.getIssuerName());
        } else {
            log.warn(">>> 쿠폰 등록 실패");
        }
    }

    /*종료*/
    public int closeCoupon(String cId) {
        return admincouponMapper.closeCoupon(cId);
    }

    /*상세 보기*/
    public CouponDTO getCouponDetail(String cId) {
        return admincouponMapper.selectCouponDetail(cId);
    }

}
