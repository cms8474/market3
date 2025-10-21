package kr.co.team3.policy_service;

import kr.co.team3.policy_dto.TermsDTO;
import kr.co.team3.policy_mapper.TermsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TermsService {

    private final TermsMapper termsMapper;

    public TermsDTO getTerms(String name) {
        return termsMapper.findByName(name);
    }
}
