package kr.co.team3.controller.my;

import kr.co.team3.dto.my.ReviewDTO;
import kr.co.team3.service.my.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/my/reivew/write")
    public void writeReview(ReviewDTO reviewDTO){
        reviewService.writeReview(reviewDTO);
    }
}
