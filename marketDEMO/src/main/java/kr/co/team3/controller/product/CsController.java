package kr.co.team3.controller.product;

import jakarta.servlet.http.HttpSession;
import kr.co.team3.product_dto.CsDTO;
import kr.co.team3.product_entity.MemberEntity;
import kr.co.team3.product_service.CsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 고객센터 (공지사항 / FAQ / QNA)
 * URL 구조:
 * /cs/index
 * /cs/notice/list
 * /cs/notice/view
 * /cs/faq/list
 * /cs/faq/view
 * /cs/qna/list
 * /cs/qna/view
 * /cs/qna/write
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/cs")
public class CsController {

    private final CsService csService;

    /** ✅ 고객센터 메인 */
    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("noticeList", csService.getLatest("NOTICE", 5));
        model.addAttribute("faqList", csService.getLatest("FAQ", 5));
        model.addAttribute("qnaList", csService.getLatest("QNA", 5));
        return "inc/cs/index"; // ✅ templates/inc/cs/index.html
    }

    /** ✅ 공지사항 목록 */
    @GetMapping("/notice/list")
    public String noticeList(@RequestParam(defaultValue = "1") int pg, Model model) {
        Page<CsDTO> list = csService.getList("NOTICE", pg, 10);
        model.addAttribute("list", list);
        model.addAttribute("boardType", "NOTICE");
        return "inc/cs/notice/list"; // ✅ templates/inc/cs/notice/list.html
    }

    /** ✅ 공지사항 상세보기 */
    @GetMapping("/notice/view")
    public String noticeView(@RequestParam("no") String no, Model model) {
        model.addAttribute("board", csService.getDetail(no));
        model.addAttribute("boardType", "NOTICE");
        return "inc/cs/notice/view"; // ✅ templates/inc/cs/notice/view.html
    }

    /** ✅ FAQ 목록 */
    @GetMapping("/faq/list")
    public String faqList(@RequestParam(defaultValue = "1") int pg, Model model) {
        Page<CsDTO> list = csService.getList("FAQ", pg, 10);
        model.addAttribute("list", list);
        model.addAttribute("boardType", "FAQ");
        return "inc/cs/faq/list"; // ✅ templates/inc/cs/faq/list.html
    }

    /** ✅ FAQ 상세보기 */
    @GetMapping("/faq/view")
    public String faqView(@RequestParam("no") String no, Model model) {
        model.addAttribute("board", csService.getDetail(no));
        model.addAttribute("boardType", "FAQ");
        return "inc/cs/faq/view"; // ✅ templates/inc/cs/faq/view.html
    }

    /** ✅ QNA 목록 */
    @GetMapping("/qna/list")
    public String qnaList(@RequestParam(defaultValue = "1") int pg, Model model) {
        Page<CsDTO> list = csService.getList("QNA", pg, 10);
        model.addAttribute("list", list);
        model.addAttribute("boardType", "QNA");
        return "inc/cs/qna/list"; // ✅ templates/inc/cs/qna/list.html
    }

    /** ✅ QNA 상세보기 */
    @GetMapping("/qna/view")
    public String qnaView(@RequestParam("no") String no, Model model) {
        model.addAttribute("board", csService.getDetail(no));
        model.addAttribute("boardType", "QNA");
        return "inc/cs/qna/view"; // ✅ templates/inc/cs/qna/view.html
    }

    /** ✅ QNA 작성 폼 */
    @GetMapping("/qna/write")
    public String qnaWriteForm(HttpSession session, Model model) {
        MemberEntity loginMember = (MemberEntity) session.getAttribute("loginMember");

        // 로그인 상태를 모델에 추가 (템플릿에서 사용)
        model.addAttribute("isLoggedIn", loginMember != null);
        model.addAttribute("loginMember", loginMember);
        model.addAttribute("board", new CsDTO());
        return "inc/cs/qna/write"; // ✅ templates/inc/cs/qna/write.html
    }

    /** ✅ QNA 작성 처리 */
    @PostMapping("/qna/write")
    public String qnaWrite(@ModelAttribute CsDTO dto, HttpSession session) {
        MemberEntity loginMember = (MemberEntity) session.getAttribute("loginMember");

        if (loginMember == null) {
            return "redirect:/member/login";
        }

        dto.setBoardType("QNA");
        dto.setBoardWriter(loginMember.getUId());
        // boardType1이 설정되지 않은 경우 기본값 설정
        if (dto.getBoardType1() == null || dto.getBoardType1().trim().isEmpty()) {
            dto.setBoardType1("기타");
        }
        csService.write(dto);

        return "redirect:/cs/qna/list";
    }
}
