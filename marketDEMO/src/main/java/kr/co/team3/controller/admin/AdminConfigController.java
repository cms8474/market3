package kr.co.team3.controller.admin;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.team3.admin_entity.Banner;
import kr.co.team3.admin_service.BannerService;
import kr.co.team3.admin_service.PolicyService;
import kr.co.team3.admin_service.VersionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import lombok.RequiredArgsConstructor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/admin/config")
@RequiredArgsConstructor
public class AdminConfigController {
    private final VersionService versionService;
    private final BannerService bannerService;
    private final PolicyService policyService;

    @Value("${app.upload-dir:upload}")
    private String uploadDir;

    @GetMapping("/basic")
    public String basic(Model model) {
        var v = versionService.getLatestVersion();

        model.addAttribute("pageTitle", "기본설정");
        model.addAttribute("activeMenu", "config-basic");
        model.addAttribute("contentFragment", "inc/admin/config/basic :: content");
        model.addAttribute("pageCss", "/css/admin_config_basic.css");

        // 화면에 뿌릴 최신 레코드
        model.addAttribute("v", v);

        return "admin";
    }


    @PostMapping("/basic")
    public String saveBasic(@RequestParam String action,
                            // 사이트
                            @RequestParam(required = false) String siteTitle,
                            @RequestParam(required = false) String siteSubtitle,

                            //로고/파비콘
                            @RequestParam(required = false) MultipartFile logoHeader,
                            @RequestParam(required = false) MultipartFile logoFooter,
                            @RequestParam(required = false) MultipartFile favicon,

                            // 회사
                            @RequestParam(required = false) String companyName,
                            @RequestParam(required = false) String ceoName,
                            @RequestParam(required = false) String bizRegNo,
                            @RequestParam(required = false) String mailOrderNo,
                            @RequestParam(required = false) String addr1,
                            @RequestParam(required = false) String addr2,

                            // 고객센터
                            @RequestParam(required = false) String csTel,
                            @RequestParam(required = false) String csHours,
                            @RequestParam(required = false) String csEmail,
                            @RequestParam(required = false) String disputeTel,

                            // 저작권
                            @RequestParam(required = false) String copyright,

                            RedirectAttributes ra) {

        switch (action) {
            case "save-site" -> {
                versionService.updateSite(siteTitle, siteSubtitle);
                ra.addFlashAttribute("msg", "사이트 정보가 저장되었습니다.");
            }
            case "save-logo" -> {
                try {
                    saveIfPresent(logoHeader, "logo-header.png");
                    saveIfPresent(logoFooter, "logo-footer.png");
                    saveIfPresent(favicon,    "favicon.ico");
                    ra.addFlashAttribute("msg", "로고/파비콘이 저장되었습니다.");
                } catch (IOException e) {
                    ra.addFlashAttribute("msg", "파일 저장 중 오류가 발생했습니다: " + e.getMessage());
                }
            }
            case "save-company" -> {
                String fullAddr = joinAddr(addr1, addr2);
                versionService.updateCompany(companyName, ceoName, bizRegNo, mailOrderNo, fullAddr);
                ra.addFlashAttribute("msg", "기업 정보가 저장되었습니다.");
            }
            case "save-support" -> {
                versionService.updateSupport(csTel, csHours, csEmail, disputeTel);
                ra.addFlashAttribute("msg", "고객센터 정보가 저장되었습니다.");
            }
            case "save-copyright" -> {
                versionService.updateCopyright(copyright);
                ra.addFlashAttribute("msg", "카피라이트가 저장되었습니다.");
            }
            default -> {
            }
        }

        return "redirect:/admin/config/basic";
    }

    private void saveIfPresent(MultipartFile file, String targetFileName) throws IOException {
        if (file != null && !file.isEmpty() && StringUtils.hasText(file.getOriginalFilename())) {
            Path dir = Paths.get(uploadDir);
            if (!Files.exists(dir)) Files.createDirectories(dir);
            Path target = dir.resolve(targetFileName);
            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    private String joinAddr(String a1, String a2) {
        a1 = (a1 == null) ? "" : a1.trim();
        a2 = (a2 == null) ? "" : a2.trim();
        if (a1.isEmpty()) return a2;
        if (a2.isEmpty()) return a1;
        return a1 + " " + a2;
    }

    @GetMapping("/banner")
    public String banner( @RequestParam(name="tab", required=false, defaultValue="MAIN_TOP") String tab,Model model,
                          HttpServletRequest request) {

        request.getAttribute(CsrfToken.class.getName());

        model.addAttribute("pageTitle","배너관리");
        model.addAttribute("activeMenu", "config-banner");
        model.addAttribute("contentFragment", "inc/admin/config/banner :: content");
        model.addAttribute("pageCss", "/css/config/admin_config_banner.css");
        model.addAttribute("tab", tab);

        model.addAttribute("banners", bannerService.list());

        return "admin";

    }
    @PostMapping("/banner")
    public String createBanner(@RequestParam String action,
                               @RequestParam(name= "name") String name,
                               @RequestParam(name= "size", required = false) String size,
                               @RequestParam(name= "bgColor", required = false) String bgColor,
                               @RequestParam(name= "linkUrl", required = false) String linkUrl,
                               @RequestParam(name= "position") String position,
                               @RequestParam(name= "startDate", required = false) LocalDate startDate,
                               @RequestParam(name= "endDate", required = false) LocalDate endDate,
                               @RequestParam(name= "startTime", required = false) String startTimeStr,
                               @RequestParam(name= "endTime", required = false) String endTimeStr,
                               @RequestParam(name= "image", required = false) MultipartFile image,
                               RedirectAttributes ra)
        {
        if (!"create".equalsIgnoreCase(action)) {
            return "redirect:/admin/config/banner?tab=" + position;
        }
            log.info("[BANNER] POST in: action={}, name={}, position={}, hasImage={}",
                    action, name, position, image!=null && !image.isEmpty());
        try {
            LocalDateTime startDateTime = composeDateTime(startDate, startTimeStr);
            LocalDateTime endDateTime = composeDateTime(endDate, endTimeStr);
            Banner saved = bannerService.create(
                    name, size, bgColor, linkUrl, position,
                    startDate, endDate, startDateTime, endDateTime, image
            );
            log.info("[BANNER] saved id={}", saved.getANo());
            ra.addFlashAttribute("msg", "배너가 등록되었습니다.");
        } catch (Exception e) {
            log.error("[BANNER] create failed", e);
            ra.addFlashAttribute("msg", "error: " + e.getMessage());
        }
            return "redirect:/admin/config/banner?tab=" + position;
        }

    @PostMapping(value = "/banner/delete", consumes = "application/json")
    @ResponseBody
    public Map<String, Object> deleteBanners(@RequestBody List<Long> ids) {
        bannerService.deleteByIds(ids);
        return Map.of("ok", true, "count", ids.size());
    }

    private LocalDateTime composeDateTime(LocalDate d, String hm) {
        if (d == null || hm == null || hm.isBlank()) return null;
        return LocalDateTime.of(d, LocalTime.parse(hm)); // "13:30" -> 13:30
    }


    @GetMapping("/policy")
    public String policy(HttpServletRequest request, Model model) {
        request.getSession(true);
        model.addAttribute("pageTitle", "약관관리");
        model.addAttribute("activeMenu", "config-policy");
        model.addAttribute("contentFragment", "inc/admin/config/policy :: content");

        model.addAttribute("buyerTos",    policyService.getBody(PolicyService.BUYER_TOS));
        model.addAttribute("sellerTos",   policyService.getBody(PolicyService.SELLER_TOS));
        model.addAttribute("financeTos",  policyService.getBody(PolicyService.FINANCE_TOS));
        model.addAttribute("locationTos", policyService.getBody(PolicyService.LOCATION_TOS));
        model.addAttribute("privacyTos",  policyService.getBody(PolicyService.PRIVACY_TOS));
        return "admin";
    }

    @PostMapping("/policy")
    public String savePolicy(@RequestParam String action,
                             @RequestParam(required = false) String buyerTos,
                             @RequestParam(required = false) String sellerTos,
                             @RequestParam(required = false) String financeTos,
                             @RequestParam(required = false) String locationTos,
                             @RequestParam(required = false) String privacyTos,
                             RedirectAttributes ra) {
        switch (action) {
            case "save-buyer-tos" -> {
                policyService.saveBody(PolicyService.BUYER_TOS, nullToEmpty(buyerTos));
                ra.addFlashAttribute("msg", "구매회원 약관이 저장되었습니다.");
            }
            case "save-seller-tos" -> {
                policyService.saveBody(PolicyService.SELLER_TOS, nullToEmpty(sellerTos));
                ra.addFlashAttribute("msg", "판매회원 약관이 저장되었습니다.");
            }
            case "save-finance-tos" -> {
                policyService.saveBody(PolicyService.FINANCE_TOS, nullToEmpty(financeTos));
                ra.addFlashAttribute("msg", "전자금융거래 약관이 저장되었습니다.");
            }
            case "save-location-tos" -> {
                policyService.saveBody(PolicyService.LOCATION_TOS, nullToEmpty(locationTos));
                ra.addFlashAttribute("msg", "위치정보 이용약관이 저장되었습니다.");
            }
            case "save-privacy-tos" -> {
                policyService.saveBody(PolicyService.PRIVACY_TOS, nullToEmpty(privacyTos));
                ra.addFlashAttribute("msg", "개인정보 처리방침이 저장되었습니다.");
            }
            default -> ra.addFlashAttribute("msg", "알 수 없는 요청입니다.");
        }
        return "redirect:/admin/config/policy";
    }
    private String nullToEmpty(String s) { return s == null ? "" : s; }


    @GetMapping("/category")
    public String category(Model model) {
        model.addAttribute("pageTitle", "카테고리 관리");
        model.addAttribute("activeMenu", "config-category");
        model.addAttribute("contentFragment", "inc/admin/config/category :: content");

        // DB 대신 임시 하드코딩 (실제로는 Service/Repository 통해 조회)
        List<Map<String, Object>> categories = new ArrayList<>();
        Map<String, Object> cat1 = new LinkedHashMap<>();
        cat1.put("name", "여성패션");
        cat1.put("sub", new ArrayList<>(Arrays.asList("브랜드의류", "브랜드가방", "브랜드신발", "브랜드잡화")));
        Map<String, Object> cat2 = new LinkedHashMap<>();
        cat2.put("name", "남성패션");
        cat2.put("sub", new ArrayList<>(Arrays.asList("셔츠", "바지")));

        categories.add(cat1);
        categories.add(cat2);

        model.addAttribute("categories", categories);

        return "admin";
    }

    /** 카테고리 순서 저장 */
    @PostMapping("/category/reorder")
    @ResponseBody
    public Map<String, Object> reorder(@RequestBody List<Map<String, Object>> newOrder) {
        // TODO: DB 저장 (여기서는 단순 출력만)
        System.out.println("새 순서 = " + newOrder);
        return Map.of("status", "ok");
    }

    /** 카테고리 삭제 */
    @PostMapping("/category/delete")
    @ResponseBody
    public Map<String, Object> delete(@RequestParam String name) {
        // TODO: DB 삭제 처리
        System.out.println("삭제할 항목: " + name);
        return Map.of("status", "deleted");
    }

}