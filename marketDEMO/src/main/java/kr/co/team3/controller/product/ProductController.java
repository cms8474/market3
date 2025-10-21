package kr.co.team3.controller.product;

import kr.co.team3.dto.product.PageRequestDTO;
import kr.co.team3.dto.product.PageResponseDTO;
import kr.co.team3.dto.product.ProductDTO;
import kr.co.team3.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// 강민철 2025-10-21 1703

@Controller
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;

    @GetMapping("/product/list")
    public String productList(@RequestParam(required = false) String type) {
        return "inc/product/list";
    }

    @GetMapping("/product/view")
    public String productView() {
        return "inc/product/view";
    }


    @GetMapping("/product/cart")
    public String productCart() {
        return "inc/product/cart";
    }


    @GetMapping("/product/order")
    public String productOrder() {
        return "inc/product/order";
    }


    @GetMapping("/product/complete")
    public String productComplete() {
        return "inc/product/complete";
    }


    @GetMapping("/product/search")
    public String productSearch(Model model, PageRequestDTO pageRequestDTO, String keyword) {
        // log.info("pageRequestDTO={}", pageRequestDTO);
        List<ProductDTO> searchedList = productService.searchWithKeyword(pageRequestDTO);
        int total = productService.countWithKeyword(pageRequestDTO);
        PageResponseDTO pageResponseDTO = PageResponseDTO.builder()
                .dtoList(searchedList)
                .pageRequestDTO(pageRequestDTO)
                .total(total)
                .build();
        model.addAttribute(pageResponseDTO);
        // log.info("pageResponseDTO={}", pageResponseDTO);
        return "inc/product/search";
    }


}
