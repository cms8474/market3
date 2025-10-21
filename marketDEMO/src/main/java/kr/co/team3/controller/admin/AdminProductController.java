package kr.co.team3.controller.admin;

import kr.co.team3.admin_dto.PageRequestDTO;
import kr.co.team3.admin_dto.PageResponseDTO;
import kr.co.team3.admin_dto.ProductStatusDTO;
import kr.co.team3.admin_service.ProductStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminProductController {

    private final ProductStatusService service;

    /*------------------product------------------*/
    /** 상품현황 리스트 */


    @GetMapping("/product/list")
    public String productList(PageRequestDTO pageRequestDTO, Model model) {

        PageResponseDTO<ProductStatusDTO> pageResponseDTO = service.getPage(pageRequestDTO);
        model.addAttribute(pageResponseDTO);

        return "admin/product/list";
    }


    @PostMapping("/product/delete")
    public String deleteOne(@RequestParam String pid, PageRequestDTO req, RedirectAttributes rttr) {
        int n = service.deleteOne(pid);
        rttr.addFlashAttribute("msg", n > 0 ? "삭제되었습니다." : "대상이 없습니다.");
        // 상태 유지 파라미터들 붙여서 목록으로
        rttr.addAttribute("pg", req.getPg());
        rttr.addAttribute("size", req.getSize());
        rttr.addAttribute("searchType", req.getSearchType());
        rttr.addAttribute("keyword", req.getKeyword());
        return "redirect:/admin/product/list";
    }

    @PostMapping("/product/delete-bulk")
    public String deleteBulk(@RequestParam("pids") List<String> pids, PageRequestDTO req, RedirectAttributes rttr) {
        int n = service.deleteBulk(pids);
        rttr.addFlashAttribute("msg", n + "건 삭제되었습니다.");
        rttr.addAttribute("pg", req.getPg());
        rttr.addAttribute("size", req.getSize());
        rttr.addAttribute("searchType", req.getSearchType());
        rttr.addAttribute("keyword", req.getKeyword());
        return "redirect:/admin/product/list";
    }





    /*-------------------------------------------------*/
    @GetMapping(value = {"/product/register"})
    public String productregister(){
        System.out.println("go productlist");
        return "admin/product/register";
    }
}
