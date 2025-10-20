package kr.co.team3.service.my;

import kr.co.team3.dto.my.BoardDTO;
import kr.co.team3.entity.my.Board;
import kr.co.team3.repository.my.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// 강민철 2025-10-20 1457

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public List<BoardDTO> getBoardsWithUidAndBtTypeRecent5(String uId, String bBtType) {
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "bRegDate");
        List<Board> boardList = boardRepository.findBybUIdAndBoardTypeBtTypeContaining(uId, bBtType, pageable);
        List<BoardDTO> dtoList = boardList.stream().map(Board::ToDTO).toList();
        return dtoList.stream().map(dto -> {
            String formatted = dto.getBRegDate().substring(0, 10);
            dto.setBRegDate(formatted);
            return dto;
        }).toList();
    }

    public int getNumberOfBoardsWithUidAndBtType(String uId, String bBtType) {
        return boardRepository.countBybUIdAndBoardTypeBtTypeContaining(uId, bBtType);
    }
}
