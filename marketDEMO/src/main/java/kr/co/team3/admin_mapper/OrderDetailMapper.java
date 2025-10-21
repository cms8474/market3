package kr.co.team3.admin_mapper;

import kr.co.team3.admin_dto.OrderDetailDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderDetailMapper {

    // 상세(헤더)
    OrderDetailDTO selectOrderDetailHeader(@Param("poNo") String poNo);

    // 상세(상품)
    List<OrderDetailDTO.OrderItemDTO> selectOrderDetailItems(@Param("poNo") String poNo);

    // 배송 프리필
    OrderDetailDTO selectShipPrefill(@Param("poNo") String poNo);

    // 배송 입력
    int updateShipmentForOrder(OrderDetailDTO dto);
    int updateShipmentForOneItem(OrderDetailDTO.OrderItemDTO itemDto); // (선택)

    // 헤더 상태 배송중
}
