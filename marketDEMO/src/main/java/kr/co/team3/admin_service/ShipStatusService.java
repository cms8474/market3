package kr.co.team3.admin_service;

import kr.co.team3.admin_dto.OrderDeliveryDetailDTO;
import kr.co.team3.admin_dto.PageRequestDTO;
import kr.co.team3.admin_dto.PageResponseDTO;
import kr.co.team3.admin_dto.ShipStatusDTO;
import kr.co.team3.admin_mapper.ShipStatusDetailMapper;
import kr.co.team3.admin_mapper.ShipStatusMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipStatusService {

    private final ShipStatusMapper shipStatusMapper;
    private final ShipStatusDetailMapper shipStatusDetailMapper;

    public PageResponseDTO<ShipStatusDTO> getPage(PageRequestDTO req) {

        int total = shipStatusMapper.countShipList(req);
        List<ShipStatusDTO> list = shipStatusMapper.selectShipList(req);

        return new PageResponseDTO<>(req, list, total);
    }

    /** 주문번호로 상세 조회 */
    @Transactional(readOnly = true)
    public OrderDeliveryDetailDTO getByPoNo(String poNo) {
        if (poNo == null || poNo.isBlank()) return null;
        OrderDeliveryDetailDTO dto = shipStatusDetailMapper.selectDetailByPoNo(poNo);
        // 안전 가드: null 리스트 방지
        if (dto != null && dto.getItems() == null) dto.setItems(java.util.List.of());
        return dto;
    }

    /** 송장번호로 상세 조회 */
    @Transactional(readOnly = true)
    public OrderDeliveryDetailDTO getByTrackingNum(String trackingNum) {
        if (trackingNum == null || trackingNum.isBlank()) return null;
        String poNo = shipStatusDetailMapper.findPoNoByTracking(trackingNum);
        if (poNo == null) return null;
        return getByPoNo(poNo);
    }

    /**
     * 파라미터 하나만 넘어와도 동작하는 편의 메서드
     *  - poNo 우선, 없으면 trackingNum 사용
     */
    @Transactional(readOnly = true)
    public OrderDeliveryDetailDTO getDetail(String poNo, String trackingNum) {
        if (poNo != null && !poNo.isBlank()) {
            return getByPoNo(poNo);
        }
        if (trackingNum != null && !trackingNum.isBlank()) {
            return getByTrackingNum(trackingNum);
        }
        return null;
    }
}
