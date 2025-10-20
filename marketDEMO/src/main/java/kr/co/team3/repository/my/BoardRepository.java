package kr.co.team3.repository.my;

import kr.co.team3.entity.my.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// 강민철 2025-10-20 1457

@Repository
public interface BoardRepository extends JpaRepository<Board, String> {
    List<Board> findBybUIdAndBoardTypeBtTypeContaining(String bUId, String boardTypeBtType, Pageable pageable);
    int countBybUIdAndBoardTypeBtTypeContaining(String bUId, String boardTypeBtType);
}
