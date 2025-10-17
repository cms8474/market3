package kr.co.team3.mapper.my;

import kr.co.team3.dto.my.PointDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PointMapper {
    public List<PointDTO> selectRecent5History(@Param("uId") String uId);
    public int selectPoints(@Param("uId") String uId);
}
