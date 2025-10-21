package kr.co.team3.controller.admin;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.team3.admin_dto.*;
import kr.co.team3.admin_mapper.AdminCategoryMapper;
import kr.co.team3.admin_service.AdminCategoryService;
import kr.co.team3.admin_service.AdminProductService;
import kr.co.team3.admin_service.ProductStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
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


    /*------------------product------------------*/

    /**
     * 상품현황 리스트
     */


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
    public String productregister() {
        System.out.println("go productlist");
        return "admin/product/register";
    }


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


    @PostMapping("/product/register")
    public String register(
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

            // 이미지
            @RequestParam("listImage") MultipartFile listImage,
            @RequestParam("mainImage") MultipartFile mainImage,
            @RequestParam("detailImage") MultipartFile detailImage,
            @RequestParam(value = "infoImage", required = false) MultipartFile infoImage,

            // 고시 notice.*
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

            // 기타
            Principal principal,
            HttpServletRequest request,
            RedirectAttributes rttr
    ) {
        try {
            // 판매자 ID
            String sellerId = (principal != null) ? principal.getName() : "SELLER001";

            // 1) Item DTO
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

            // 2) Detail DTO
            ProductDetailDTO detail = new ProductDetailDTO();
            detail.setState(nTag);
            detail.setTax(nTax);
            detail.setReceipt(nReceipt);
            detail.setSellerType(nBizType);
            detail.setBrand(nBrand);
            detail.setOrigin(nOrigin);
            detail.setMaker(StringUtils.hasText(nMaker) ? nMaker : maker); // 폼 maker와 notice.maker 중 우선순위 조정 가능
            detail.setCountry(nCountry);
            detail.setCare(nCaution);
            detail.setManufDate(nMfgDate);
            detail.setWarranty(nWarranty);
            detail.setAsManager(nAsManager);
            detail.setPhone(nPhone);

            // 3) Option DTOs (options[i].name + options[i].items CSV)
            List<ProductOptionDTO> options = parseOptions(request);

            // 4) 서비스 호출
            String pid = productService.register(item, detail, options, listImage, mainImage, detailImage, infoImage);

            rttr.addFlashAttribute("msg", "등록 완료: " + pid);
            return "redirect:/admin/product/list";
        } catch (Exception e) {
            log.error("상품 등록 실패", e);
            rttr.addFlashAttribute("msg", "등록 실패: " + e.getMessage());
            return "redirect:/admin/product/register";
        }
    }

    /** request 파라미터에서 options[i].name/items 를 파싱해서 행 리스트 생성 */
    private List<ProductOptionDTO> parseOptions(HttpServletRequest request) {
        List<ProductOptionDTO> list = new ArrayList<>();

        // 인덱스 0부터 연속된 형태를 가정하고 루프
        for (int i = 0; i < 50; i++) {
            String name = request.getParameter("options[" + i + "].name");
            String itemsCsv = request.getParameter("options[" + i + "].items");
            if (!StringUtils.hasText(name) && !StringUtils.hasText(itemsCsv)) {
                // 연속 인덱스가 끊기면 종료
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
                o.setAddPrice(0); // 필요 시 폼 확장
                o.setStock(999);  // 필요 시 폼 확장
                list.add(o);
            }
        }
        return list;
    }
}
