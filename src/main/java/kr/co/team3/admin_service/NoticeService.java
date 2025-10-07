package kr.co.team3.admin_service;

import kr.co.team3.admin_dto.NoticeSummaryDTO;
import kr.co.team3.admin_repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import kr.co.team3.admin_entity.Notice;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public List<NoticeSummaryDTO> latest5() {
        return noticeRepository.findTop5ByStatusOrderByCreatedAtDesc("Y")
                .stream()
                .map(n -> new NoticeSummaryDTO(
                        n.getId(),
                        n.getTitle(),
                        n.getWriter(),
                        n.getCreatedAt()
                ))
                .toList();
    }
}