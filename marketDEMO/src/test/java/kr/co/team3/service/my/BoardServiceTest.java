package kr.co.team3.service.my;

import jakarta.transaction.Transactional;
import kr.co.team3.entity.my.Board;
import kr.co.team3.repository.my.BoardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    @Transactional
    void getBoardsWithUidAndBtType() {
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "bRegDate");
        String uId = "user01";
        String bBtType = "qna";
        List<Board> boardList = boardRepository.findBybUIdAndBoardTypeBtTypeContaining(uId, bBtType, pageable);
//        List<Board> boardList = boardRepository.findBybUId(uId);
        Assertions.assertNotNull(boardList);
        System.out.println(boardList);
    }
}