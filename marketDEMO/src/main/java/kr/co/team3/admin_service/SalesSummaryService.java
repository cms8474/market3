package kr.co.team3.admin_service;

import kr.co.team3.admin_dto.PageRequestDTO;
import kr.co.team3.admin_dto.PageResponseDTO;
import kr.co.team3.admin_dto.SalesSummaryDTO;
import kr.co.team3.admin_mapper.SalesSummaryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesSummaryService {

    private final SalesSummaryMapper mapper;

    /**
     * 매출현황 목록 + 페이징 정보 반환
     * PageResponseDTO<SalesSummaryDTO> 형태로 반환
     */
    public PageResponseDTO<SalesSummaryDTO> getList(PageRequestDTO pageRequestDTO) {

        // ===== 1. 기간 필터 기본값 설정 =====
        ZoneId zone = ZoneId.of("Asia/Seoul");
        LocalDate baseDate = pageRequestDTO.getBaseDate() != null
                ? pageRequestDTO.getBaseDate()
                : LocalDate.now(zone);
        String periodType = pageRequestDTO.getPeriodType() != null
                ? pageRequestDTO.getPeriodType()
                : "day";

        // ===== 2. 기간 계산 =====
        LocalDateTime startLdt;
        LocalDateTime endLdt;

        switch (periodType) {
            case "week" -> {
                // 이번 주 월요일 ~ 기준일 다음날 00시
                LocalDate weekStart = baseDate.with(DayOfWeek.MONDAY);
                startLdt = weekStart.atStartOfDay();
                endLdt = baseDate.plusDays(1).atStartOfDay();
            }
            case "month" -> {
                // 이번 달 1일 ~ 기준일 다음날 00시
                LocalDate monthStart = baseDate.with(TemporalAdjusters.firstDayOfMonth());
                startLdt = monthStart.atStartOfDay();
                endLdt = baseDate.plusDays(1).atStartOfDay();
            }
            default -> {
                // 일별: 기준일 00시 ~ 다음날 00시
                startLdt = baseDate.atStartOfDay();
                endLdt = baseDate.plusDays(1).atStartOfDay();
            }
        }

        OffsetDateTime startAt = startLdt.atZone(zone).toOffsetDateTime();
        OffsetDateTime endAt = endLdt.atZone(zone).toOffsetDateTime();

        int offset = pageRequestDTO.getOffset();
        int limit = pageRequestDTO.getSize();

        List<SalesSummaryDTO> list = mapper.selectSalesSummaryList(
                pageRequestDTO, offset, limit, startAt, endAt);
        int total = mapper.countSalesSummary(pageRequestDTO, startAt, endAt);

        return new PageResponseDTO<>(pageRequestDTO, list, total);
    }
}
