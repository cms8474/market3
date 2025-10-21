package kr.co.team3.controller.company;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import kr.co.team3.company_service.RecruitService;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import kr.co.team3.company_entity.RecruitEntity;

@Controller
@RequiredArgsConstructor
public class CompanyController {

    private final RecruitService recruitService;

    @GetMapping("/company/index")
    public String index(){
        return "company/index";
    }

    @GetMapping("/company/culture")
    public String culture(){
        return "company/culture";
    }

    @GetMapping("/company/story")
    public String story(){
        return "company/story";
    }

    @GetMapping("/company/recruit")
    public String recruit(@RequestParam(defaultValue = "1") int page, Model model) {

        int size = 5; // 한 페이지당 게시글 개수
        List<RecruitEntity> recruitList = recruitService.getRecruitPage(page, size);
        int totalCount = recruitService.getTotalCount();
        int totalPages = (int) Math.ceil((double) totalCount / size);

        model.addAttribute("recruitList", recruitList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "company/recruit";
    }

    @GetMapping("/company/media")
    public String media(){
        return "company/media";
    }
}
