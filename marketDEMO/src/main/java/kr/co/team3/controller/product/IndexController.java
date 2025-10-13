package kr.co.team3.controller.product;

import kr.co.team3.product_service.IndexService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("productIndexController") // ✅ Bean 이름을 명시적으로 지정
@RequiredArgsConstructor
@RequestMapping("/product")
public class IndexController {

    private final IndexService indexService;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("mainBanners", indexService.getMainBanners());
        model.addAttribute("slideBanners", indexService.getSlideBanners());
        model.addAttribute("categories", indexService.getCategories());
        model.addAttribute("bestProducts", indexService.getBestProducts());
        model.addAttribute("hitProducts", indexService.getHitProducts());
        model.addAttribute("recommendProducts", indexService.getRecommendProducts());
        model.addAttribute("latestProducts", indexService.getLatestProducts());
        model.addAttribute("discountProducts", indexService.getDiscountProducts());
        model.addAttribute("companyInfo", indexService.getCompanyInfo());
        return "index";
    }
}
