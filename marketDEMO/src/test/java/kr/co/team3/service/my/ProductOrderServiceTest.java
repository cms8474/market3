package kr.co.team3.service.my;

import kr.co.team3.dto.my.ProductOrderDTO;
import kr.co.team3.mapper.my.ProductOrderMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
}