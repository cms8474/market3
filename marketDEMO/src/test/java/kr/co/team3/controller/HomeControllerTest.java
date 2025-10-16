package kr.co.team3.controller;

import kr.co.team3.dto.my.ProductOrderDTO;
import kr.co.team3.service.my.ProductOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HomeControllerTest {
    @Autowired
    private ProductOrderService productOrderService;

    @Test
    void home() {
        List<ProductOrderDTO> dtoList = productOrderService.getRecent5("user01");
        Assert.notNull(dtoList, "불러오기 실패");
        System.out.println(dtoList);
    }

    @Test
    void orderDetail() {
        List<ProductOrderDTO> dtoList = productOrderService.get1Order("user01", "user01_0002");
        Assert.notNull(dtoList, "불러오기 실패");
        System.out.println(dtoList);
    }
}