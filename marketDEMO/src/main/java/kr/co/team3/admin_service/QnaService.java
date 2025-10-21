package kr.co.team3.admin_service;

import kr.co.team3.admin_dto.QnaSummaryDTO;
import kr.co.team3.admin_entity.Qna;
import kr.co.team3.admin_repository.QnaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class QnaService {

    private final QnaRepository qnaRepository;

    public List<QnaSummaryDTO> top5() {
        List<Qna> list = qnaRepository.findTop5ByOrderByCreatedAtDesc();
        log.info("[QNA] top5 size={}", list.size());
        return list.stream()
                .map(q -> new QnaSummaryDTO(q.getId(), q.getTitle(), q.getQuestioner(), q.getCreatedAt()))
                .toList();
}
}