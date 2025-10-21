package kr.co.team3.admin_repository;

import kr.co.team3.admin_entity.Qna;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QnaRepository extends JpaRepository<Qna, String> {

    List<Qna> findTop5ByOrderByCreatedAtDesc();
}