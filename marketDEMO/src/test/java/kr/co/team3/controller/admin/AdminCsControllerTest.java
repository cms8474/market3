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
        RecruitmentDTO dto = RecruitmentDTO.builder()
                .r_dept("개발팀")
                .r_career("신입")
                .r_type("정규직")
                .r_title("테스트용 채용 공고")
                .r_status("모집중")
                .r_start_date("2025-10-14")
                .r_end_date("2025-10-30")
                .build();

        RecruitmentDTO saved = recruitmentService.save(dto);

        Assertions.assertNotNull(saved.getR_no());
        Assertions.assertEquals("개발팀", saved.getR_dept());
        Assertions.assertEquals("테스트용 채용 공고", saved.getR_title());

        Recruitment entity = recruitmentRepository.findById(saved.getR_no()).orElse(null);
        Assertions.assertNotNull(entity);
        Assertions.assertEquals("정규직", entity.getR_type());
    }

    @Test
    void noticeWrite() {
    }

    @Test
    void noticeWritePost() {
    }
}