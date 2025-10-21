package kr.co.team3.admin_mapper;

import kr.co.team3.admin_dto.PageRequestDTO;
import kr.co.team3.admin_dto.ShipStatusDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShipStatusMapper {
    int countShipList(PageRequestDTO req);
    List<ShipStatusDTO> selectShipList(PageRequestDTO req);
}
