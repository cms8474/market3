package kr.co.team3.admin_repository;

import kr.co.team3.admin_dto.QnaSummaryDTO;
import kr.co.team3.admin_entity.Qna; // 실제 엔티티 패키지에 맞게 수정
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QnaRepository extends JpaRepository<Qna, Long> {

    List<Qna> findTop5ByStatusOrderByCreatedAtDesc(String status);
}