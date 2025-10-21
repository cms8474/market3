package kr.co.team3.admin_service;

import kr.co.team3.admin_dto.PageRequestDTO;
import kr.co.team3.admin_dto.PageResponseDTO;
import kr.co.team3.admin_dto.ShipStatusDTO;
import kr.co.team3.admin_mapper.ShipStatusMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipStatusService {

    private final ShipStatusMapper shipStatusMapper;

    public PageResponseDTO<ShipStatusDTO> getPage(PageRequestDTO req) {

        int total = shipStatusMapper.countShipList(req);
        List<ShipStatusDTO> list = shipStatusMapper.selectShipList(req);

        return new PageResponseDTO<>(req, list, total);
    }
}
