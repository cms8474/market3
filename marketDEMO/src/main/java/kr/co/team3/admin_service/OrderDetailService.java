package kr.co.team3.admin_service;

import kr.co.team3.admin_dto.OrderDetailDTO;
import kr.co.team3.admin_mapper.OrderDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailService {

    private final OrderDetailMapper orderDetailMapper;

    /** 주문 상세 헤더 + 상품목록 조회 */
    public OrderDetailDTO getOrderDetail(String poNo) {
        OrderDetailDTO header = orderDetailMapper.selectOrderDetailHeader(poNo);
        if (header == null) return null;
        List<OrderDetailDTO.OrderItemDTO> items = orderDetailMapper.selectOrderDetailItems(poNo);
        header.setItems(items);
        return header;
    }

    /** 배송입력 모달 프리필 */
    public OrderDetailDTO getShipPrefill(String poNo) {
        return orderDetailMapper.selectShipPrefill(poNo);
    }

    /** 배송등록 (ORDER_ITEMS + PRODUCT_ORDER) */
    @Transactional
    public void registerShipment(OrderDetailDTO dto) {
        // 아이템 배송정보 업데이트
        orderDetailMapper.updateShipmentForOrder(dto);
        // 주문 마스터 상태 변경
    }
}
