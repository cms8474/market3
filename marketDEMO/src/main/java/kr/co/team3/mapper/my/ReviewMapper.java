package kr.co.team3.mapper.my;

import kr.co.team3.dto.my.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReviewMapper {
    public void insertReview(@Param("reviewDTO") ReviewDTO reviewDTO);
    public void deleteReview(@Param("prNo") String prNo);
}
