package kr.co.team3.admin_service;

import kr.co.team3.admin_dto.NoticeSummaryDTO;
import kr.co.team3.admin_entity.Notice;
import kr.co.team3.admin_repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public List<NoticeSummaryDTO> top5() {
        List<Notice> list = noticeRepository.findTop5ByOrderByCreatedAtDesc();
        log.info("[NOTICE] top5 size={}", list.size());
        return list.stream()
                .map(n -> new NoticeSummaryDTO(n.getId(), n.getTitle(), n.getCreatedAt()))
                .toList();
    }
}