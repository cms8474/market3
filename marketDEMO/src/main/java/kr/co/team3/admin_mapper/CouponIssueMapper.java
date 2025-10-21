package kr.co.team3.admin_mapper;

import kr.co.team3.admin_dto.CouponIssueDTO;
import kr.co.team3.admin_dto.PageRequestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CouponIssueMapper {
    List<CouponIssueDTO> selectIssueList(@Param("req") PageRequestDTO req);
    int countIssueList(@Param("req") PageRequestDTO req);
    CouponIssueDTO selectIssueByPoNo(@Param("poNo") String poNo);
    int stopIssue(@Param("poNo") String poNo);


}