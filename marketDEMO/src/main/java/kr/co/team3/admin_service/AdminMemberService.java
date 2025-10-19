package kr.co.team3.admin_service;

import kr.co.team3.admin_dto.PageRequestDTO;
import kr.co.team3.admin_dto.PageResponseDTO;
import kr.co.team3.product_dto.MemberDTO;
import kr.co.team3.product_mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminMemberService {

    private final MemberMapper memberMapper;
    private final ModelMapper modelMapper;

    public PageResponseDTO<MemberDTO> selectAll(PageRequestDTO pageRequestDTO) {

        log.info(">>> selectAll() called. pg={}, size={}",
                pageRequestDTO.getPg(), pageRequestDTO.getSize());

        List<MemberDTO> dtoList = memberMapper.selectMemberList(pageRequestDTO);

        // ✅ 리스트 null 방탄
        if (dtoList == null) {
            log.info(">>> selectMemberList returned NULL; using empty list");
            dtoList = Collections.emptyList();
        } else {
            long nullCount = dtoList.stream().filter(Objects::isNull).count();
            log.info(">>> member list fetched: size={}, nullElements={}", dtoList.size(), nullCount);

            if (nullCount > 0) {
                dtoList = dtoList.stream().filter(Objects::nonNull).collect(Collectors.toList());
                log.info(">>> member list sanitized: sizeAfterFilter={}", dtoList.size());
            }
        }

        int total = memberMapper.selectCountTotal(pageRequestDTO);
        log.info(">>> total count={}", total);

        // ✅ 빌더 대신 생성자 직접 호출 (제너릭 타입 자동 추론)
        return new PageResponseDTO<>(pageRequestDTO, dtoList, total);
    }

    public void update() {}
}
