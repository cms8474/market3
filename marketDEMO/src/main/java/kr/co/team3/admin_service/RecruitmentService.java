package kr.co.team3.admin_service;

import kr.co.team3.admin_dto.RecruitmentDTO;
import kr.co.team3.admin_entity.Recruitment;
import kr.co.team3.admin_repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;

    public List<Recruitment> findAll(){

        return recruitmentRepository.findAll();
    }
    public Recruitment findById(int r_no){

        return null;
    }

    public RecruitmentDTO save(RecruitmentDTO recruitmentDTO) {
        Recruitment saved = recruitmentRepository.save(recruitmentDTO.toEntity());

        return saved.toDTO();
    }


    public void delete(int r_no){
        recruitmentRepository.deleteById(r_no);
    }

    public void deleteAllById(List<Integer> ids) {
        recruitmentRepository.deleteAllById(ids);
    }


}
