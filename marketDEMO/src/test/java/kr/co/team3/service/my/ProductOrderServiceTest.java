package kr.co.team3.service.my;

import jakarta.transaction.Transactional;
import kr.co.team3.dto.my.PageRequestDTO;
import kr.co.team3.dto.my.ProductOrderDTO;
import kr.co.team3.mapper.my.ProductOrderMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;

// 강민철 2025-10-20 1710

@SpringBootTest
class ProductOrderServiceTest {

    @Autowired
    ProductOrderMapper productOrderMapper;

    @Test
    void getRecent5() {
        List<ProductOrderDTO> dtoList = productOrderMapper.selectRecent5WithU_id("user01");
        Assertions.assertNotNull(dtoList);
        System.out.println(dtoList);
    }

    @Test
    @Transactional
    void selectAll() {
        String uId = "user01";
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .pg(1)
//                .recentMonths("2025-09")
                .build();
        List<ProductOrderDTO> dtoList = productOrderMapper.selectAll(uId, pageRequestDTO);
        dtoList.forEach(dto -> {
            String formatted =  dto.getPoOrderdate().substring(0, 10);
            dto.setPoOrderdate(formatted.substring(0,10));
        });
        Assertions.assertNotNull(dtoList);
        System.out.println(dtoList);
    }
}