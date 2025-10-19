package kr.co.team3.admin_mapper;

import kr.co.team3.admin_dto.PageRequestDTO;
import kr.co.team3.admin_dto.PointHistoryListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminPointHistoryMapper {

    List<PointHistoryListDTO> selectPointHistoryList(PageRequestDTO pageRequestDTO);

    int selectCountTotal(PageRequestDTO pageRequestDTO);
    int deletePointHistory(@Param("uhNo") String uhNo); // 단건 삭제
    int deletePointHistoryList(@Param("list") List<String> uhNoList); // 다중 삭제
}
