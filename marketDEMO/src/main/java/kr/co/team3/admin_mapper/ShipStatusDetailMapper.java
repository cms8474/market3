package kr.co.team3.admin_mapper;

import kr.co.team3.admin_dto.OrderDeliveryDetailDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShipStatusDetailMapper {

    /** 송장번호로 주문번호 찾기 (상세용) */
    String findPoNoByTracking(@Param("trackingNum") String trackingNum);

    /** 주문번호로 상세 조회 (헤더 + 아이템 목록까지 한 방에 매핑) */
    OrderDeliveryDetailDTO selectDetailByPoNo(@Param("poNo") String poNo);

    /** (내부 사용) 아이템 목록 서브쿼리 */
    List<OrderDeliveryDetailDTO.Item> selectItemsByPoNo(@Param("poNo") String poNo);
}
