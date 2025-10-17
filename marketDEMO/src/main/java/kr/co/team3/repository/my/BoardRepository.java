package kr.co.team3.repository.my;

import kr.co.team3.admin_repository.JpaRepository;
import kr.co.team3.entity.my.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, String> {
    public List<Board> findByBUIdAndBBtTypeContainingOrderByBRegDateDesc(String bUId, String bBtType, Pageable pageable);
}
