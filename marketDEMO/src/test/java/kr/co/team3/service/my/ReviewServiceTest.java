package kr.co.team3.service.my;

import kr.co.team3.dto.my.ReviewDTO;
import kr.co.team3.mapper.my.ReviewMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewServiceTest {

    @Autowired
    ReviewMapper reviewMapper;

    @Test
    void writeReview() {
        ReviewDTO reviewDTO = ReviewDTO.builder()
                .prPoNo("user01_0001")
                .prPPid("seller01_100001_85870138")
                .prUId("user01")
                .prStar(4)
                .prBody("괜찮네요.")
                .build();

        reviewMapper.insertReview(reviewDTO);
        reviewMapper.deleteReview("user01_seller01_100001_85870138_01");
    }
}