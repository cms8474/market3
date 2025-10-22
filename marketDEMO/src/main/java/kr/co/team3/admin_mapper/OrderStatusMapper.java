package kr.co.team3.admin_mapper;

import kr.co.team3.admin_dto.OrderStatusDTO;
import kr.co.team3.admin_dto.PageRequestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderStatusMapper {


    List<OrderStatusDTO> selectOrderStatusPage(@Param("req") PageRequestDTO req);
    int countOrderStatus(@Param("req") PageRequestDTO req);
    int updateToDelivered();
    int updateToShipping();
}
