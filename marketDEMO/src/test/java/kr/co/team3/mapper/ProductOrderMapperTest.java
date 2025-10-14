package kr.co.team3.mapper;

import kr.co.team3.dto.my.ProductOrderDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductOrderMapperTest {

    @Autowired
    ProductOrderMapper productOrderMapper;

    @Test
    void selectRecent5WithUID() {
        List<ProductOrderDTO> dtoList = productOrderMapper.selectRecent5WithUID("user01");
        assertNotNull(dtoList);
        System.out.println(dtoList);
    }
}