package kr.co.team3.admin_repository;

import kr.co.team3.admin_entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findTop5ByStatusOrderByCreatedAtDesc(String status);
}
