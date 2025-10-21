package kr.co.team3.controller.product;

import kr.co.team3.dto.product.PageRequestDTO;
import kr.co.team3.dto.product.ProductDTO;
import kr.co.team3.service.product.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// 강민철 2025-10-21 1703

@SpringBootTest
@AutoConfigureMockMvc
@MockitoBean(types = ProductService.class)
class ProductControllerTest {
    @Autowired MockMvc mvc;

    @Autowired ProductService productService;

    @Test
    void productSearch() throws Exception {
        // given
        List<ProductDTO> fake = List.of(Mockito.mock(ProductDTO.class), Mockito.mock(ProductDTO.class));
        Mockito.when(productService.searchWithKeyword(any(PageRequestDTO.class)))
                .thenReturn(fake);

        // when & then
        mvc.perform(get("/product/search")
                                .param("keyword", "phone")
                                .param("pg", "1")          // PageRequestDTO 바인딩 필드(필요 시 size/sort 등 추가)
                        // .param("size","10")
                )
                .andExpect(status().isOk())
                .andExpect(view().name("inc/product/search"))
                .andExpect(model().attributeExists("searchedList"))
                .andExpect(model().attribute("searchedList", hasSize(2)));

        // 서비스가 올바른 파라미터로 호출됐는지 검증
        Mockito.verify(productService).searchWithKeyword(any(PageRequestDTO.class));
        // List<ProductDTO> searchedList = productService.searchWithKeyword(keyword, pageRequestDTO);
        // Assertions.assertNotNull(searchedList);
        // System.out.println(searchedList);
    }
}