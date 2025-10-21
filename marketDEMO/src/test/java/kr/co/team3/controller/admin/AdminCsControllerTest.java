package kr.co.team3.controller.admin;

import kr.co.team3.admin_dto.RecruitmentDTO;
import kr.co.team3.admin_entity.Recruitment;
import kr.co.team3.admin_repository.RecruitmentRepository;
import kr.co.team3.admin_service.RecruitmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AdminCsControllerTest {

    @Autowired
    private RecruitmentService recruitmentService;

    @Autowired
    private RecruitmentRepository recruitmentRepository;

    @Test
    void recruitList() {
    }
    @Test
    void recruitRegister() {


    }

    @Test
    void noticeWrite() {
    }

    @Test
    void noticeWritePost() {
    }
}