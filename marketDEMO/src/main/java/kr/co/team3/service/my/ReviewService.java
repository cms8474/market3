package kr.co.team3.service.my;

import kr.co.team3.dto.my.PageRequestDTO;
import kr.co.team3.dto.my.PageResponseDTO;
import kr.co.team3.dto.my.ReviewDTO;
import kr.co.team3.mapper.my.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// 강민철 2025-10-20 1710

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewMapper reviewMapper;

    public void writeReview(ReviewDTO reviewDTO) {
        reviewMapper.insertReview(reviewDTO);
    }

    public void deleteReview(String prNo) {
        reviewMapper.deleteReview(prNo);
    }

    public List<ReviewDTO> getReviewsRecent5(String uId) {
        List<ReviewDTO> dtoList = reviewMapper.selectRecent5(uId);
        return dtoList.stream().map(dto -> {
            String formatted = dto.getPrRegDate().substring(0,11);
            dto.setPrRegDate(formatted);
            return dto;
        }).toList();
    }
    public PageResponseDTO selectAll(String uId, PageRequestDTO pageRequestDTO){
        List<ReviewDTO> dtoList = reviewMapper.selectAll(uId, pageRequestDTO);
        dtoList.forEach(dto -> {
            String formatted =  dto.getPrRegDate().substring(0, 10);
            dto.setPrRegDate(formatted.substring(0,10));
        });

        int total = reviewMapper.selectCountReview(uId);

        return PageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .reviewDTOList(dtoList)
                .total(total)
                .build();
    }
}
