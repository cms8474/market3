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

import java.security.Principal;
import java.time.LocalDateTime;
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


        Map<String, String> codeNameMap = boardTypeService.getCodeNameMap("noti");

        model.addAttribute("page", page);
        model.addAttribute("q", q);
        model.addAttribute("cate", catePrefix);
        model.addAttribute("codeNameMap", codeNameMap);

        return "admin/cs/notice/noticeList";
    }

    /*공지사항들록*/
    @GetMapping("/notice/write")
    public String noticeWrite(Model model) {
        Map<String, String> typeMap = boardTypeService.getCodeNameMap("noti");
        model.addAttribute("dto", new CsDTO());
        model.addAttribute("typeMap", typeMap);
        return "admin/cs/notice/noticeWrite";
    }

    @PostMapping("/notice/write")
    public String noticeWritePost(@ModelAttribute("dto") CsDTO dto, Principal principal) {
        log.info("POST /admin/cs/notice/write dto={}", dto);

        dto.setBoardWriter(principal != null ? principal.getName() : "admin01");
        log.info(" 작성자(boardWriter): {}", dto.getBoardWriter());

        dto.setBoardView(0);
        dto.setBoardRegDate(LocalDateTime.now());

        String id = csService.saveByType(dto);
        return "redirect:/admin/cs/notice/view?id=" + id;
    }



    /*공지사항 수정*/


    @GetMapping("/notice/modify")
    public String noticeModify(@RequestParam("id") String id, Model model) {
        var dto = csService.getDetail(id);
        if (dto == null) {
            return "redirect:/admin/cs/notice/list";
        }

        Map<String, String> typeMap = boardTypeService.getCodeNameMap("noti");

        model.addAttribute("dto", dto);
        model.addAttribute("typeMap", typeMap);
        return "admin/cs/notice/noticeModify";
    }

    @PostMapping("/notice/modify")
    public String noticeModifyPost(@ModelAttribute("dto") CsDTO dto, Principal principal) {
        log.info("POST /admin/cs/notice/modify id={} title={}", dto.getBoardId(), dto.getBoardTitle());

        // 작성자 확인 (안 바꿔도 됨)
        dto.setBoardWriter(principal != null ? principal.getName() : "admin01");
        dto.setBoardRegDate(LocalDateTime.now());

        csService.update(dto);
        return "redirect:/admin/cs/notice/view?id=" + dto.getBoardId();
    }


    /*공지사항 상세*/
    @GetMapping("/notice/view")
    public String noticeView(@RequestParam("id") String id, Model model) {
        var dto = csService.getDetail(id);
        if (dto == null) {
            return "redirect:/admin/cs/notice/list";
        }
        Map<String, String> codeNameMap = boardTypeService.getCodeNameMap("noti");

        model.addAttribute("dto", dto);
        model.addAttribute("codeNameMap", codeNameMap);
        return "admin/cs/notice/noticeView";
    }

    @GetMapping("/notice/view/{id}")
    public String noticeViewPath(@PathVariable String id, Model model) {
        return noticeView(id, model);
    }


    /* 공지사항 삭제 */
    @GetMapping("/notice/delete")
    public String noticeDelete(@RequestParam("id") String id) {
        log.info("삭제 요청: {}", id);

        csService.delete(id);
        log.info("삭제 완료 - {}", id);
        return "redirect:/admin/cs/notice/list?deleted=true";
    }



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

        Map<String, String> codeNameMap = boardTypeService.getCodeNameMap("faq");

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
    public String faqModify(@RequestParam("id") String id, Model model) {
        var dto = csService.getDetail(id);
        if (dto == null) {
            return "redirect:/admin/cs/faq/list";
        }

        model.addAttribute("dto", dto);
        model.addAttribute("lv1List", boardTypeService.getLv1("faq"));
        model.addAttribute("lv2Tree", boardTypeService.getLv2Tree("faq"));
        return "admin/cs/faq/faqModify";
    }

    @PostMapping("/faq/modify")
    public String faqModifyPost(@ModelAttribute("dto") CsDTO dto, Principal principal) {
        log.info("POST /admin/cs/faq/modify id={} title={}", dto.getBoardId(), dto.getBoardTitle());

        // 작성자 확인 (안 바꿔도 됨)
        dto.setBoardWriter(principal != null ? principal.getName() : "admin01");
        dto.setBoardRegDate(LocalDateTime.now());

        csService.update(dto);
        return "redirect:/admin/cs/faq/view?id=" + dto.getBoardId();
    }





    @GetMapping("/faq/write")
    public String faqWrite(Model model) {
        model.addAttribute("dto", new CsDTO());
        model.addAttribute("lv1List", boardTypeService.getLv1("faq"));
        model.addAttribute("lv2Tree", boardTypeService.getLv2Tree("faq"));
        return "admin/cs/faq/faqWrite";
    }
    @PostMapping("/faq/write")
    public String faqWritePost(@ModelAttribute("dto") CsDTO dto, Principal principal) {
        dto.setBoardWriter(principal != null ? principal.getName() : "admin01");
        dto.setBoardView(0);
        dto.setBoardRegDate(LocalDateTime.now());

        String id = csService.saveByType(dto);
        return "redirect:/admin/cs/faq/view?id=" + id;
    }

    @GetMapping("/faq/view")
    public String faqView(@RequestParam("id") String id, Model model) {
        var dto = csService.getDetail(id);
        if (dto == null) {
            return "redirect:/admin/cs/faq/list";
        }

        // 1차, 2차 유형명 매핑
        Map<String, String> codeNameMap = boardTypeService.getCodeNameMap("faq");
        String lv1Name = "-";
        String lv2Name = "-";

        if (dto.getBoardType() != null) {
            String lv1Code = dto.getBoardType().substring(0, 4) + "0"; // 예: faq21 -> faq20
            lv1Name = codeNameMap.getOrDefault(lv1Code, "-");
            lv2Name = codeNameMap.getOrDefault(dto.getBoardType(), "-");
        }

        model.addAttribute("dto", dto);
        model.addAttribute("lv1Name", lv1Name);
        model.addAttribute("lv2Name", lv2Name);

        return "admin/cs/faq/faqView";
    }



    @GetMapping("/faq/view/{id}")
    public String faqViewPath(@PathVariable String id, Model model) {
        return faqView(id, model); // 위 메서드 재사용
    }


    /*  삭제 */
    @GetMapping("/faq/delete")
    public String faqDelete(@RequestParam("id") String id) {
        log.info("삭제 요청: {}", id);

        csService.delete(id);
        log.info("삭제 완료 - {}", id);
        return "redirect:/admin/cs/faq/list?deleted=true";
    }

    /*------------------ QnA ------------------*/

    @GetMapping("/qna/list")
    public String qnaList(@RequestParam(required = false) String q,
                          @RequestParam(name = "cate", required = false) String catePrefix,
                          @PageableDefault(size = 10, sort = "boardRegDate") Pageable pageable,
                          Model model) {

        Page<CsDTO> page = (q != null || catePrefix != null)
                ? csService.searchByPrefix("qna", catePrefix, q, pageable)
                : csService.getListByPrefix("qna", pageable);

        Map<String, String> codeNameMap = boardTypeService.getCodeNameMap("qna");


        model.addAttribute("page", page);
        model.addAttribute("q", q);
        model.addAttribute("cate", catePrefix);
        model.addAttribute("codeNameMap", codeNameMap);

        model.addAttribute("lv1List", boardTypeService.getLv1("qna"));
        model.addAttribute("lv2Tree", boardTypeService.getLv2Tree("qna"));

        return "admin/cs/qna/qnaList";
    }

    @GetMapping("/qna/view")
    public String qnaView(@RequestParam("id") String id, Model model) {
        var dto = csService.getDetail(id);
        if (dto == null) {
            return "redirect:/admin/cs/qna/list";
        }

        // 1차, 2차 유형명 매핑
        Map<String, String> codeNameMap = boardTypeService.getCodeNameMap("qna");
        String lv1Name = "-";
        String lv2Name = "-";

        if (dto.getBoardType() != null) {
            String lv1Code = dto.getBoardType().substring(0, 4) + "0"; // 예: faq21 -> faq20
            lv1Name = codeNameMap.getOrDefault(lv1Code, "-");
            lv2Name = codeNameMap.getOrDefault(dto.getBoardType(), "-");
        }

        model.addAttribute("dto", dto);
        model.addAttribute("lv1Name", lv1Name);
        model.addAttribute("lv2Name", lv2Name);

        return "admin/cs/qna/qnaView";
    }



    @GetMapping("/qna/view/{id}")
    public String qnaViewPath(@PathVariable String id, Model model) {
        return qnaView(id, model); // 위 메서드 재사용
    }


    @GetMapping("/qna/reply")
    public String qnaReply(@RequestParam("id") String id, Model model) {
        CsDTO dto = csService.getDetail(id);
        if (dto == null) {
            return "redirect:/admin/cs/qna/list";
        }

        // 코드 → 이름 매핑
        Map<String, String> codeNameMap = boardTypeService.getCodeNameMap("qna");
        String lv1Name = "-";
        String lv2Name = "-";

        String lv1Code = dto.getLv1Code();   // 예: qna20
        String lv2Code = dto.getBoardType(); // 예: qna21

        if (lv1Code != null && !lv1Code.isBlank()) {
            lv1Name = codeNameMap.getOrDefault(lv1Code, "-");
        }
        if (lv2Code != null && !lv2Code.isBlank()) {
            lv2Name = codeNameMap.getOrDefault(lv2Code, "-");
        }

        model.addAttribute("dto", dto);
        model.addAttribute("lv1Name", lv1Name);
        model.addAttribute("lv2Name", lv2Name);

        return "admin/cs/qna/qnaReply";
    }

    @PostMapping("/qna/reply")
    public String qnaReplyPost(@ModelAttribute("dto") CsDTO dto, Principal principal) {
        log.info("답변 등록 요청: id={}, answer={}", dto.getBoardId(), dto.getBoardAnswer());

        // 작성자 확인 (안 바꿔도 됨)
        dto.setBoardWriter(principal != null ? principal.getName() : "admin01");
        dto.setBoardRegDate(LocalDateTime.now());

        csService.updateAnswer(dto);
        return "redirect:/admin/cs/qna/view?id=" + dto.getBoardId();
    }

    /*  삭제 */
    @GetMapping("/qna/delete")
    public String qnaDelete(@RequestParam("id") String id) {
        log.info("삭제 요청: {}", id);

        csService.delete(id);
        log.info("삭제 완료 - {}", id);
        return "redirect:/admin/cs/qna/list?deleted=true";
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
