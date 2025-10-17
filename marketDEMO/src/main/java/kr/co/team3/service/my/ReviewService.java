package kr.co.team3.service.my;

import kr.co.team3.dto.my.ReviewDTO;
import kr.co.team3.mapper.my.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewMapper reviewMapper;

//    public List<ReviewDTO> getAllReviews(String u_id) {
//    }

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
}
