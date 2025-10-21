package kr.co.team3.controller.my;

import kr.co.team3.dto.my.*;
import kr.co.team3.mapper.my.PointMapper;
import kr.co.team3.repository.my.BoardRepository;
import kr.co.team3.service.my.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

// 강민철 2025-10-21 1214

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    private final ProductOrderService productOrderService;
    private final PointService pointService;
    private final ReviewService reviewService;
    private final BoardService boardService;
    private final CouponService couponService;

    @GetMapping("/my/home")
    public String home(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginId = null;
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                loginId =  ((UserDetails) principal).getUsername(); // 아이디 반환
            }
        }
        List<ProductOrderDTO> orderDTOList = productOrderService.getRecent5(loginId);
        model.addAttribute("orderDTOList", orderDTOList);
        List<PointDTO> pointDTOList = pointService.getPointListRecent5(loginId);
        model.addAttribute("pointDTOList", pointDTOList);
        List<ReviewDTO> reviewDTOList = reviewService.getReviewsRecent5(loginId);
        model.addAttribute("reviewDTOList", reviewDTOList);
        List<BoardDTO> qnaDTOList = boardService.getBoardsWithUidAndBtTypeRecent5(loginId, "qna");
        model.addAttribute("qnaDTOList", qnaDTOList);
//        log.info(qnaDTOList.toString());

        return "my/home";
    }

    @GetMapping("/my/modal/orderDetail/{u_id}/{po_no}")
    @ResponseBody
    public ResponseEntity<List<ProductOrderDTO>> orderDetail(@PathVariable("u_id") String u_id, @PathVariable("po_no") String po_no){
        List<ProductOrderDTO> orderDTOList = productOrderService.getOrder1(u_id, po_no);
        return ResponseEntity.ok(orderDTOList);
    }




    @GetMapping("/my/coupon")
    public String coupon(){
        return "my/coupon";
    }



    @GetMapping("/my/qna")
    public String qna(){
        return "my/qna";
    }

    @GetMapping("/my/info")
    public String info(){
        return "my/info";
    }

}
