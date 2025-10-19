package kr.co.team3.admin_mapper;

import kr.co.team3.admin_dto.PageRequestDTO;
import kr.co.team3.admin_dto.SalesSummaryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.OffsetDateTime;
import java.util.List;

@Mapper
public interface SalesSummaryMapper {

    List<SalesSummaryDTO> selectSalesSummaryList(
            @Param("pageRequestDTO") PageRequestDTO pageRequestDTO,
            @Param("offset") int offset,
            @Param("limit") int limit,
            @Param("startAt") OffsetDateTime startAt,
            @Param("endAt") OffsetDateTime endAt
    );
    int countSalesSummary(
            @Param("pageRequestDTO") PageRequestDTO pageRequestDTO,
            @Param("startAt") java.time.OffsetDateTime startAt,
            @Param("endAt") java.time.OffsetDateTime endAt
    );
}
