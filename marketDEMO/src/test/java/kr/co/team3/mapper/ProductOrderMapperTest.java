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
    void selectRecent5WithU_id() {
        List<ProductOrderDTO> dtoList = productOrderMapper.selectRecent5WithU_id("user01");
        assertNotNull(dtoList);
        System.out.println(dtoList);
    }

    @Test
    void selectWithU_idAndPo_noAndP_pid() {
        ProductOrderDTO dto = productOrderMapper.selectWithU_idAndPo_noAndP_pid("user01",
                "user01_0002", "seller01_200001_73459186");
        assertNotNull(dto);
        System.out.println(dto);
    }
}