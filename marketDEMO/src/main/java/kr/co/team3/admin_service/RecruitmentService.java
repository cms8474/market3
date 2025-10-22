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

    private String normalizeType(String st) {
        if (st == null) return null;
        return switch (st.trim().toLowerCase()) {
            // snake
            case "r_no"     -> "recruitNo";
            case "r_dept"   -> "recruitDept";
            case "r_type"   -> "recruitType";
            case "r_title"  -> "recruitTitle";
            // camel
            case "recruitno"    -> "recruitNo";
            case "recruitdept"  -> "recruitDept";
            case "recruittype"  -> "recruitType";
            case "recruittitle" -> "recruitTitle";
            default -> null;
        };
    }

    /** 페이징 + 검색 */
    public Page<Recruitment> findPage(String searchType, String keyword, Pageable pageable) {
        String type = normalizeType(searchType);
        if (type == null || keyword == null || keyword.isBlank()) {
            return recruitmentRepository.findAll(pageable);
        }

        return switch (type) {
            case "recruitNo" -> {
                try {
                    Integer no = Integer.valueOf(keyword.trim());
                    yield recruitmentRepository.findByRecruitNo(no, pageable);
                } catch (NumberFormatException e) {
                    yield Page.empty(pageable);
                }
            }
            case "recruitDept"  -> recruitmentRepository.findByRecruitDeptContainingIgnoreCase(keyword, pageable);
            case "recruitType"  -> recruitmentRepository.findByRecruitTypeContainingIgnoreCase(keyword, pageable);
            case "recruitTitle" -> recruitmentRepository.findByRecruitTitleContainingIgnoreCase(keyword, pageable);
            default             -> recruitmentRepository.findAll(pageable);
        };
    }

    public List<Recruitment> findAll() {
        return recruitmentRepository.findAll();
    }

    public Recruitment findById(int recruitNo) {
        return recruitmentRepository.findById(recruitNo).orElse(null);
    }

    public RecruitmentDTO save(RecruitmentDTO dto) {
        Recruitment saved = recruitmentRepository.save(dto.toEntity());
        return saved.toDTO();
    }

    public void delete(int recruitNo) {
        recruitmentRepository.deleteById(recruitNo);
    }

    public void deleteAllById(List<Integer> ids) {
        recruitmentRepository.deleteAllById(ids);
    }
}
