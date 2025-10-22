package kr.co.team3.controller.admin;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.team3.admin_dto.*;
import kr.co.team3.admin_mapper.UserMapper;
import kr.co.team3.admin_service.AdminCategoryService;
import kr.co.team3.admin_service.AdminProductService;
import kr.co.team3.admin_service.ProductStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminProductController {

    private final ProductStatusService service;
    private final AdminCategoryService categoryService;
    private final AdminProductService productService;
    private final UserMapper userMapper;

    /* ------------------ 상품 목록 ------------------ */
    @GetMapping("/product/list")
    public String productList(PageRequestDTO req, Model model, Principal principal) {

        // 비로그인 처리 (선택): 로그인 요구 또는 전체 차단
        if (principal == null) {
            // return "redirect:/login";
            // 혹은 전체 보여주지 않음 처리
        }

        String loginId = (principal != null) ? principal.getName() : null;
        String userType = (loginId != null) ? userMapper.selectUserType(loginId) : null;

        req.setSellerId(loginId);
        req.setUserType(userType);

        PageResponseDTO<ProductStatusDTO> pageResponseDTO = service.getPage(req);
        model.addAttribute(pageResponseDTO);
        return "admin/product/list";
    }


    /* ------------------ 상품 등록 페이지 ------------------ */
    @GetMapping("/product/register")
    public String productRegister() {
        return "admin/product/register";
    }

    /* ------------------ 카테고리 AJAX ------------------ */
    @GetMapping("/product/categories/level1")
    @ResponseBody
    public List<AdminCategoryDTO> level1() {
        return categoryService.getLevel1();
    }

    @GetMapping("/product/categories/level2")
    @ResponseBody
    public List<AdminCategoryDTO> level2(@RequestParam String parentId) {
        return categoryService.getLevel2(parentId);
    }

    /* ------------------ 상품 등록 처리 ------------------ */
    @PostMapping("/product/register")
    public String register(
            @RequestParam String cat1,
            @RequestParam(required = false) String cat2,
            @RequestParam String name,
            @RequestParam String summary,
            @RequestParam(required = false) String maker,
            @RequestParam Integer price,
            @RequestParam(required = false) Integer discountRate,
            @RequestParam(required = false) Integer point,
            @RequestParam(required = false) Integer stock,
            @RequestParam(required = false) Integer deliveryFee,
            @RequestParam("listImage") MultipartFile listImage,
            @RequestParam("mainImage") MultipartFile mainImage,
            @RequestParam("detailImage") MultipartFile detailImage,
            @RequestParam(value = "infoImage", required = false) MultipartFile infoImage,
            @RequestParam(value = "notice.tag", required = false) String nTag,
            @RequestParam(value = "notice.taxType", required = false) String nTax,
            @RequestParam(value = "notice.receipt", required = false) String nReceipt,
            @RequestParam(value = "notice.bizType", required = false) String nBizType,
            @RequestParam(value = "notice.brand", required = false) String nBrand,
            @RequestParam(value = "notice.origin", required = false) String nOrigin,
            @RequestParam(value = "notice.maker", required = false) String nMaker,
            @RequestParam(value = "notice.country", required = false) String nCountry,
            @RequestParam(value = "notice.caution", required = false) String nCaution,
            @RequestParam(value = "notice.mfgDate", required = false) String nMfgDate,
            @RequestParam(value = "notice.warranty", required = false) String nWarranty,
            @RequestParam(value = "notice.asManager", required = false) String nAsManager,
            @RequestParam(value = "notice.phone", required = false) String nPhone,
            Principal principal,
            HttpServletRequest request,
            RedirectAttributes rttr
    ) {
        try {
            String sellerId = (principal != null) ? principal.getName() : "SELLER001";

            ProductItemDTO item = new ProductItemDTO();
            item.setName(name);
            item.setDesc(summary);
            item.setPrice(price);
            item.setDiscount(discountRate);
            item.setPoint(point);
            item.setStockQuantity(stock);
            item.setDeliveryPrice(deliveryFee);
            item.setSellerId(sellerId);
            item.setPcId(StringUtils.hasText(cat2) ? cat2 : cat1);

            ProductDetailDTO detail = new ProductDetailDTO();
            detail.setState(nTag);
            detail.setTax(nTax);
            detail.setReceipt(nReceipt);
            detail.setSellerType(nBizType);
            detail.setBrand(nBrand);
            detail.setOrigin(nOrigin);
            detail.setMaker(StringUtils.hasText(nMaker) ? nMaker : maker);
            detail.setCountry(nCountry);
            detail.setCare(nCaution);
            detail.setManufDate(nMfgDate);
            detail.setWarranty(nWarranty);
            detail.setAsManager(nAsManager);
            detail.setPhone(nPhone);

            List<ProductOptionDTO> options = parseOptions(request);

            String pid = productService.registerOriginalFileNames(
                    item, detail, options, listImage, mainImage, detailImage, infoImage
            );

            rttr.addFlashAttribute("msg", "등록 완료: " + pid);
            return "redirect:/admin/product/list";

        } catch (Exception e) {
            log.error("상품 등록 실패", e);
            rttr.addFlashAttribute("error", "등록 실패: " + e.getMessage());
            return "redirect:/admin/product/register";
        }
    }

    /* -----삭제 ------------ */

    /** 단건 삭제 */
    @PostMapping("/product/delete")
    public String deleteOne(@RequestParam String pid, PageRequestDTO req, RedirectAttributes rttr) {
        int n = service.deleteOne(pid);
        rttr.addFlashAttribute("msg", n > 0 ? "삭제되었습니다." : "대상이 없습니다.");
        // 상태 유지 파라미터
        rttr.addAttribute("pg", req.getPg());
        rttr.addAttribute("size", req.getSize());
        rttr.addAttribute("searchType", req.getSearchType());
        rttr.addAttribute("keyword", req.getKeyword());
        return "redirect:/admin/product/list";
    }

    /** 선택 삭제(복수) */
    @PostMapping("/product/delete-bulk")
    public String deleteBulk(@RequestParam("pids") List<String> pids, PageRequestDTO req, RedirectAttributes rttr) {
        int n = service.deleteBulk(pids);
        rttr.addFlashAttribute("msg", n + "건 삭제되었습니다.");
        // 상태 유지 파라미터
        rttr.addAttribute("pg", req.getPg());
        rttr.addAttribute("size", req.getSize());
        rttr.addAttribute("searchType", req.getSearchType());
        rttr.addAttribute("keyword", req.getKeyword());
        return "redirect:/admin/product/list";
    }

    /* ------------------ 옵션 파싱 ------------------ */
    private List<ProductOptionDTO> parseOptions(HttpServletRequest request) {
        List<ProductOptionDTO> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            String name = request.getParameter("options[" + i + "].name");
            String itemsCsv = request.getParameter("options[" + i + "].items");
            if (!StringUtils.hasText(name) && !StringUtils.hasText(itemsCsv)) {
                if (i > 5) break;
                else continue;
            }
            if (!StringUtils.hasText(name) || !StringUtils.hasText(itemsCsv)) continue;

            for (String raw : itemsCsv.split(",")) {
                String sel = raw.trim();
                if (sel.isEmpty()) continue;
                ProductOptionDTO o = new ProductOptionDTO();
                o.setName(name.trim());
                o.setSelection(sel);
                o.setAddPrice(0);
                o.setStock(999);
                list.add(o);
            }
        }
        return list;
    }


    // AdminProductController.java (일부 추가)

    @GetMapping("/product/modify")
    public String modifyForm(@RequestParam String pid, Model model) {
        // 수정 화면에 필요한 데이터 조회
        ProductItemDTO item = productService.getItem(pid);
        ProductDetailDTO detail = productService.getDetail(pid);
        List<ProductOptionDTO> options = productService.getOptions(pid);

        model.addAttribute("item", item);
        model.addAttribute("detail", detail);
        model.addAttribute("options", options);
        return "admin/product/modify";
    }

    @PostMapping("/product/modify")
    public String modify(
            @RequestParam String pid,

            // 카테고리
            @RequestParam String cat1,
            @RequestParam(required = false) String cat2,

            // 기본정보
            @RequestParam String name,
            @RequestParam String summary,
            @RequestParam(required = false) String maker,
            @RequestParam Integer price,
            @RequestParam(required = false) Integer discountRate,
            @RequestParam(required = false) Integer point,
            @RequestParam(required = false) Integer stock,
            @RequestParam(required = false) Integer deliveryFee,

            // 이미지(선택 업로드면 교체, 아니면 유지)
            @RequestParam(value = "listImage",   required = false) MultipartFile listImage,
            @RequestParam(value = "mainImage",   required = false) MultipartFile mainImage,
            @RequestParam(value = "detailImage", required = false) MultipartFile detailImage,
            @RequestParam(value = "infoImage",   required = false) MultipartFile infoImage,

            // notice.*
            @RequestParam(value = "notice.tag",       required = false) String nTag,
            @RequestParam(value = "notice.taxType",   required = false) String nTax,
            @RequestParam(value = "notice.receipt",   required = false) String nReceipt,
            @RequestParam(value = "notice.bizType",   required = false) String nBizType,
            @RequestParam(value = "notice.brand",     required = false) String nBrand,
            @RequestParam(value = "notice.origin",    required = false) String nOrigin,
            @RequestParam(value = "notice.maker",     required = false) String nMaker,
            @RequestParam(value = "notice.country",   required = false) String nCountry,
            @RequestParam(value = "notice.caution",   required = false) String nCaution,
            @RequestParam(value = "notice.mfgDate",   required = false) String nMfgDate,
            @RequestParam(value = "notice.warranty",  required = false) String nWarranty,
            @RequestParam(value = "notice.asManager", required = false) String nAsManager,
            @RequestParam(value = "notice.phone",     required = false) String nPhone,

            HttpServletRequest request,
            RedirectAttributes rttr
    ) {
        try {
            // 기본/상세 DTO 구성
            ProductItemDTO item = new ProductItemDTO();
            item.setPPid(pid);
            item.setName(name);
            item.setDesc(summary);
            item.setPrice(price);
            item.setDiscount(discountRate);
            item.setPoint(point);
            item.setStockQuantity(stock);
            item.setDeliveryPrice(deliveryFee);
            item.setPcId(StringUtils.hasText(cat2) ? cat2 : cat1);

            ProductDetailDTO detail = new ProductDetailDTO();
            detail.setPPid(pid);
            detail.setState(nTag);
            detail.setTax(nTax);
            detail.setReceipt(nReceipt);
            detail.setSellerType(nBizType);
            detail.setBrand(nBrand);
            detail.setOrigin(nOrigin);
            detail.setMaker(StringUtils.hasText(nMaker) ? nMaker : maker);
            detail.setCountry(nCountry);
            detail.setCare(nCaution);
            detail.setManufDate(nMfgDate);
            detail.setWarranty(nWarranty);
            detail.setAsManager(nAsManager);
            detail.setPhone(nPhone);

            List<ProductOptionDTO> options = parseOptions(request);

            productService.updateProduct(item, detail, options,
                    listImage, mainImage, detailImage, infoImage);

            rttr.addFlashAttribute("msg", "수정 완료: " + pid);
            return "redirect:/admin/product/list";
        } catch (Exception e) {
            log.error("상품 수정 실패", e);
            rttr.addFlashAttribute("error", "수정 실패: " + e.getMessage());
            return "redirect:/admin/product/modify?pid=" + pid;
        }
    }

}
