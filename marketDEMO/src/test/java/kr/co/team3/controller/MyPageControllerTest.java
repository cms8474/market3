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
class MyPageControllerTest {
    @Autowired
    private ProductOrderService productOrderService;

    @Test
    void home() {
        List<ProductOrderDTO> dtoList = productOrderService.selectRecent5WithUID("user01");
        Assert.notNull(dtoList, "불러오기 실패");
        System.out.println(dtoList);
    }
}