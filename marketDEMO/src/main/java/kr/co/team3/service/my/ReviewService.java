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
}
