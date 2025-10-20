package kr.co.team3.company_service;

import kr.co.team3.company_entity.RecruitEntity;
import kr.co.team3.company_mapper.RecruitMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecruitService {

    private final RecruitMapper recruitMapper;

    public List<RecruitEntity> getRecruitList() {
        return recruitMapper.findAll();
    }

    public List<RecruitEntity> getRecruitPage(int page, int size) {
        int offset = (page - 1) * size;
        return recruitMapper.findPaged(offset, size);
    }

    public int getTotalCount() {
        return recruitMapper.countAll();
    }

}
