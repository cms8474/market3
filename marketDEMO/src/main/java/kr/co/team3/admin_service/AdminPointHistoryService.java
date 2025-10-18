package kr.co.team3.admin_service;

import kr.co.team3.admin_dto.PageRequestDTO;
import kr.co.team3.admin_dto.PageResponseDTO;
import kr.co.team3.admin_dto.PointHistoryListDTO;
import kr.co.team3.admin_mapper.AdminPointHistoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminPointHistoryService {

    private final AdminPointHistoryMapper mapper;

    public PageResponseDTO<PointHistoryListDTO> selectAll(PageRequestDTO pageRequestDTO) {

        log.info(">>> selectAll() called. pg={}, size={}",
                pageRequestDTO.getPg(), pageRequestDTO.getSize());

        List<PointHistoryListDTO> dtoList = mapper.selectPointHistoryList(pageRequestDTO);

        if (dtoList == null) {
            log.info(">>> selectPointHistoryList returned NULL; using empty list");
            dtoList = Collections.emptyList();
        } else {
            long nullCount = dtoList.stream().filter(Objects::isNull).count();
            log.info(">>> point history fetched: size={}, nullElements={}", dtoList.size(), nullCount);

            if (nullCount > 0) {
                dtoList = dtoList.stream().filter(Objects::nonNull).collect(Collectors.toList());
                log.info(">>> point history sanitized: sizeAfterFilter={}", dtoList.size());
            }
        }

        int total = mapper.selectCountTotal(pageRequestDTO);
        log.info(">>> total count={}", total);

        return new PageResponseDTO<>(pageRequestDTO, dtoList, total);
    }


    public void deletePointHistory(String uhNo) {
        log.info(">>> deletePointHistory() called. uhNo={}", uhNo);
        int result = mapper.deletePointHistory(uhNo);
        log.info(">>> delete result={}", result);
    }

    public void deletePointHistoryList(List<String> uhNoList) {
        log.info(">>> deletePointHistoryList() called. size={}", uhNoList.size());
        int result = mapper.deletePointHistoryList(uhNoList);
        log.info(">>> batch delete result={}", result);
    }}
