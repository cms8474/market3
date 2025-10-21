package kr.co.team3.admin_service;

import kr.co.team3.admin_dto.AdminCategoryDTO;
import kr.co.team3.admin_mapper.AdminCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCategoryService {
    private final AdminCategoryMapper mapper;

    public List<AdminCategoryDTO> getLevel1() {
        return mapper.selectLevel1();
    }

    public List<AdminCategoryDTO> getLevel2(String parentId) {
        return mapper.selectLevel2(parentId);
    }
}
