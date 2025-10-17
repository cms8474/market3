package kr.co.team3.service.my;

import kr.co.team3.dto.my.PointDTO;
import kr.co.team3.mapper.my.PointMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PointService {
    private final PointMapper pointMapper;

    public List<PointDTO> getPointListRecent5(String uId){
        List<PointDTO> dtoList = pointMapper.selectRecent5History(uId);
        return dtoList.stream().map(dto ->{
            String formatted = dto.getPoOrderdate().substring(0,11);
            dto.setPoOrderdate(formatted);
            formatted = dto.getUhDday().substring(0,11);
            dto.setUhDday(formatted);
            return dto;
        }).toList();
    }

    public int getOwnPoints(String uId){
        return pointMapper.selectPoints(uId);
    }
}
