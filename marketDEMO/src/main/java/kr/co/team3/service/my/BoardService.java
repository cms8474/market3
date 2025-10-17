package kr.co.team3.service.my;

import kr.co.team3.dto.my.BoardDTO;
import kr.co.team3.entity.my.Board;
import kr.co.team3.repository.my.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public List<BoardDTO> getBoardsWithUidAndBtType(String uId, String bBtType) {
        Pageable pageable = PageRequest.of(0, 5);
        List<Board> boardList = boardRepository.findByBUIdAndBBtTypeContainingOrderByBRegDateDesc(uId, bBtType, pageable);
        return boardList.stream().map(Board::ToDTO).collect(Collectors.toList());
    }
}
