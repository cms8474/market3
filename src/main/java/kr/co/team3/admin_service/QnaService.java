package kr.co.team3.admin_service;

import kr.co.team3.admin_dto.QnaSummaryDTO;
import kr.co.team3.admin_repository.QnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class QnaService {

    private final QnaRepository qnaRepository;

    public List<QnaSummaryDTO> latest5() {
        return qnaRepository.findTop5ByStatusOrderByCreatedAtDesc("Y")
                .stream()
                .map(q -> new QnaSummaryDTO(
                        q.getId(),           // 템플릿에서 q.id
                        q.getTitle(),
                        q.getQuestioner(),   // 템플릿에서 q.questioner 마스킹
                        q.getCreatedAt(),
                        q.getAnsweredYn()
                ))
                .toList();
    }
}