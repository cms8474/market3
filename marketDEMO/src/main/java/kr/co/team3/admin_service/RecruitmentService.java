package kr.co.team3.admin_service;

import kr.co.team3.admin_entity.Recruitment;
import kr.co.team3.admin_repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public Recruitment save(Recruitment recruitment){

        return null;
    }

    public void delete(int r_no){

    }


}
