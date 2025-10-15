package kr.co.team3.controller.admin;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.team3.admin_dto.RecruitmentDTO;
import kr.co.team3.admin_entity.BoardType;
import kr.co.team3.admin_entity.Recruitment;
import kr.co.team3.admin_service.AdminCsService;
import kr.co.team3.admin_service.BoardTypeService;
import kr.co.team3.admin_service.RecruitmentService;
import kr.co.team3.product_dto.CsDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/admin/cs")
@RequiredArgsConstructor
public class AdminCsController {

    private final RecruitmentService recruitmentService;
    private final AdminCsService csService;
    private final BoardTypeService boardTypeService;

    /*------------------ Notice ------------------*/

    @GetMapping("/notice/list")
    public String noticeList(@RequestParam(required = false) String q,
                             @RequestParam(name = "cate", required = false) String catePrefix, // ex) noti01
                             @PageableDefault(size = 10, sort = "boardRegDate") Pageable pageable,
                             Model model) {
        Page<CsDTO> page = (q != null || catePrefix != null)
                ? csService.searchByPrefix("noti", catePrefix, q, pageable)
                : csService.getListByPrefix("noti", pageable);

        model.addAttribute("page", page);
        model.addAttribute("q", q);
        model.addAttribute("cate", catePrefix);
        return "admin/cs/notice/noticeList";
    }




    @GetMapping("/notice/modify")
    public String noticeModify() {
        log.info("go cs/noticeModify");
        return "admin/cs/notice/noticeModify";
    }

    @GetMapping("/notice/write")
    public String noticeWrite() {
        log.info("go cs/noticeWrite");
        return "admin/cs/notice/noticeWrite";
    }

    @GetMapping("/notice/view")
    public String noticeView() {
        log.info("go cs/noticeView");
        return "admin/cs/notice/noticeView";
    }


    // 원하는 위치(파일 상단)에 간단한 뷰 전용 레코드/클래스 추가
    record FaqRow(kr.co.team3.product_dto.CsDTO cs, String cate1Name, String cate2Name,
                  String cate1Code, String cate2Code) {}


    /*------------------ FAQ ------------------*/
    @GetMapping("/faq/list")
    public String faqList(@RequestParam(required = false) String q,
                          @RequestParam(name = "cate", required = false) String catePrefix,
                          @PageableDefault(size = 10, sort = "boardRegDate") Pageable pageable,
                          Model model) {

        q = (q != null && q.isBlank()) ? null : q;
        catePrefix = (catePrefix != null && catePrefix.isBlank()) ? null : catePrefix;

        Page<CsDTO> page = (q != null || catePrefix != null)
                ? csService.searchByPrefix("faq", catePrefix, q, pageable)
                : csService.getListByPrefix("faq", pageable);

        Map<String, String> codeNameMap = boardTypeService.getCodeNameMap("faq"); // 키는 소문자/trim 정규화된 버전

        model.addAttribute("page", page);
        model.addAttribute("q", q);
        model.addAttribute("cate", catePrefix);
        model.addAttribute("codeNameMap", codeNameMap);

        // (필요시) 선택박스용
        model.addAttribute("lv1List", boardTypeService.getLv1("faq"));
        model.addAttribute("lv2Tree", boardTypeService.getLv2Tree("faq"));
        return "admin/cs/faq/faqList";
    }





    @GetMapping("/faq/modify")
    public String faqModify() {
        log.info("go cs/faqModify");
        return "admin/cs/faq/faqModify";
    }

    @GetMapping("/faq/write")
    public String faqWrite() {
        log.info("go cs/faqWrite");
        return "admin/cs/faq/faqWrite";
    }

    @GetMapping("/faq/view")
    public String faqView() {
        log.info("go cs/faqView");
        return "admin/cs/faq/faqView";
    }


    /*------------------ QnA ------------------*/
    @GetMapping("/qna/list")
    public String qnaList(@RequestParam(required = false) String q,
                          @RequestParam(name = "cate", required = false) String catePrefix, // ex) qna03
                          @PageableDefault(size = 10, sort = "boardRegDate") Pageable pageable,
                          Model model) {
        Page<CsDTO> page = (q != null || catePrefix != null)
                ? csService.searchByPrefix("qna", catePrefix, q, pageable)
                : csService.getListByPrefix("qna", pageable);

        model.addAttribute("page", page);
        model.addAttribute("q", q);
        model.addAttribute("cate", catePrefix);
        return "admin/cs/qna/qnaList";
    }

    @GetMapping("/qna/view")
    public String qnaView() {
        log.info("go cs/qnaView");
        return "admin/cs/qna/qnaView";
    }

    @GetMapping("/qna/answer")
    public String qnaAnswer() {
        log.info("go cs/qnaAnswer");
        return "admin/cs/qna/qnaAnswer";
    }


    /*------------------ Recruit ------------------*/
    @GetMapping("/recruit/list")
    public String recruitList(HttpServletRequest request, Model model) {
        request.getSession();
        List<Recruitment> list = recruitmentService.findAll();

        log.info("recruit list size = {}", list.size());
        model.addAttribute("list", list);
        return "admin/cs/recruit/recruitList";
    }

    @PostMapping("/recruit/register")
    public String recruitRegister(RecruitmentDTO recruitmentDTO) {
        log.info("recruitmentDTO = {}", recruitmentDTO);
        recruitmentService.save(recruitmentDTO);
        return "redirect:/admin/cs/recruit/list";
    }

    @PostMapping("/recruit/delete")
    @ResponseBody
    public ResponseEntity<?> deleteRecruitments(@RequestBody List<Integer> ids) {
        try {
            recruitmentService.deleteAllById(ids);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("삭제 중 예외", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
