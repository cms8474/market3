package kr.co.team3.admin_mapper;

import kr.co.team3.admin_dto.OrderStatusDTO;
import kr.co.team3.admin_dto.PageRequestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderStatusMapper {


    List<OrderStatusDTO> selectOrderStatusList(PageRequestDTO pageRequestDTO);


    int countOrderStatus(PageRequestDTO pageRequestDTO);


    int updateToDelivered();
    int updateToShipping();
}
