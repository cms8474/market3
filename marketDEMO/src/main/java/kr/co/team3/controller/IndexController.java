package kr.co.team3.controller;

import kr.co.team3.product_dto.IndexDTO;
import kr.co.team3.product_service.IndexService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final IndexService indexService;

    @GetMapping(value = {"/","/index"})
    public String index(Model model){
        System.out.println("go index");
        
        // 각 카테고리별 상품 목록 조회
        List<IndexDTO> bestProducts = indexService.getBestProducts(); // 베스트상품
        List<IndexDTO> hitProducts = indexService.getHitProducts(); // 히트상품
        List<IndexDTO> recommendProducts = indexService.getRecommendProducts(); // 추천상품
        List<IndexDTO> latestProducts = indexService.getLatestProducts(); // 최신상품
        List<IndexDTO> popularProducts = indexService.getPopularProducts(); // 인기상품
        List<IndexDTO> discountProducts = indexService.getDiscountProducts(); // 할인상품
        
        // 모델에 데이터 추가
        model.addAttribute("bestProducts", bestProducts); // 베스트상품
        model.addAttribute("hitProducts", hitProducts); // 히트상품
        model.addAttribute("recommendProducts", recommendProducts); // 추천상품
        model.addAttribute("latestProducts", latestProducts); // 최신상품
        model.addAttribute("popularProducts", popularProducts); // 인기상품
        model.addAttribute("discountProducts", discountProducts); //할인상품
        
        return "index";
    }

    @GetMapping(value = {"/exPage"})
    public String exPage(){
        System.out.println("go exPage");
        return "exPage";
    }

}
