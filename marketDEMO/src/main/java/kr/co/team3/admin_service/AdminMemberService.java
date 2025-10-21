package kr.co.team3.admin_service;

import kr.co.team3.admin_dto.PageRequestDTO;
import kr.co.team3.admin_dto.PageResponseDTO;
import kr.co.team3.product_dto.MemberDTO;
import kr.co.team3.product_mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
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

        return new PageResponseDTO<>(pageRequestDTO, dtoList, total);
    }

    // 회원 단건 조회
    public MemberDTO selectMemberById(String u_id) {
        log.info(">>> selectMemberById() called: {}", u_id);
        return memberMapper.selectMemberById(u_id);
    }

    // 회원 단건 수정
    public void updateMember(MemberDTO dto) {
        log.info(">>> updateMember() called: {}", dto.getU_id());
        int result = memberMapper.updateMemberProfile(dto);

        log.info(">>> updateMember() result rows={}", result);

        if (result == 0) {
            log.warn(">>> updateMember() - no row updated (maybe 탈퇴 상태?): {}", dto.getU_id());
        } else {
            log.info(">>> updateMember() - successfully updated {}", dto.getU_id());
        }
    }


    /* 상태 업데이트*/
    // 정상 → 중지
    public boolean stop(String u_id) {
        int rows = memberMapper.updateStatusToStop(u_id);
        log.info("stop {} rows={}", u_id, rows);
        return rows > 0;
    }

    // 중지/휴면 → 정상
    public boolean resume(String u_id) {
        int rows = memberMapper.updateStatusToActive(u_id);
        log.info("resume {} rows={}", u_id, rows);
        return rows > 0;
    }

    // 비활성(개인정보 파기)
    public boolean deactivate(String u_id) {
        int rows = memberMapper.deactivateMember(u_id);
        log.info("deactivate {} rows={}", u_id, rows);
        return rows > 0;
    }

    // 배치: 3개월 미로그인 → 휴면
    @Scheduled(cron = "0 0 3 * * *") // 매일 새벽 3시
    public void markDormantJob() {
        int rows = memberMapper.makeDormantOver90Days();
        log.info("makeDormantOver90Days rows={}", rows);
    }

    //선택수정
    public int bulkUpdateRanks(List<MemberDTO> updates) {
        int rows = memberMapper.bulkUpdateRanks(updates);
        log.info(">>> bulkUpdateRanks rows={}", rows);
        return rows;
    }


}
