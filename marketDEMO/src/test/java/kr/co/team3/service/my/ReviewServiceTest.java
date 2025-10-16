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
                .pr_po_no("user01_0001")
                .pr_p_pid("seller01_100001_85870138")
                .pr_u_id("user01")
                .pr_star(4)
                .pr_body("괜찮네요.")
                .build();

        reviewMapper.insertReview(reviewDTO);
    }
}