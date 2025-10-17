package kr.co.team3.admin_repository;

import kr.co.team3.admin_entity.BoardType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BoardTypeRepository extends JpaRepository<BoardType, String> {
    List<BoardType> findByTypeStartingWithOrderByTypeAsc(String prefix); // "faq", "noti", "qna"
}