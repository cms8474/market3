package kr.co.team3.mapper.my;

import kr.co.team3.dto.my.PageRequestDTO;
import kr.co.team3.dto.my.PointDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

// 강민철 2025-10-20 1710

@Mapper
public interface PointMapper {
    public List<PointDTO> selectRecent5History(@Param("uId") String uId);
    public int selectPoints(@Param("uId") String uId);
    public List<PointDTO> selectAll(@Param("uId") String uId, @Param("pageRequestDTO")PageRequestDTO pageRequestDTO);
    public int selectCountHistory(@Param("uId") String uId, @Param("pageRequestDTO")PageRequestDTO pageRequestDTO);
}
