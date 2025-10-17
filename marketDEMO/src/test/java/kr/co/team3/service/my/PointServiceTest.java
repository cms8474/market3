package kr.co.team3.service.my;

import kr.co.team3.dto.my.PointDTO;
import kr.co.team3.mapper.my.PointMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PointServiceTest {

    @Autowired
    private PointMapper pointMapper;

    @Test
    void getPointListRecent5() {
        List<PointDTO> dtoList = pointMapper.selectRecent5History("user01");
        assertNotNull(dtoList);
        System.out.println(dtoList);
    }
}