package kr.co.team3.mapper.my;

import kr.co.team3.dto.my.PageRequestDTO;
import kr.co.team3.dto.my.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

// 강민철 2025-10-20 1710

@Mapper
public interface ReviewMapper {
    public void insertReview(@Param("reviewDTO") ReviewDTO reviewDTO);
    public void deleteReview(@Param("prNo") String prNo);
    public List<ReviewDTO> selectRecent5(@Param("uId") String uId);
    public List<ReviewDTO> selectAll(@Param("uId") String uId, @Param("pageRequestDTO")PageRequestDTO pageRequestDTO);
    public int selectCountReview(@Param("uId") String uId);
}
