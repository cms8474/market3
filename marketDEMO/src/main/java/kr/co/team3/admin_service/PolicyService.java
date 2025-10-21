package kr.co.team3.admin_service;

import kr.co.team3.admin_entity.Terms;
import kr.co.team3.admin_repository.PolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PolicyService {
    public static final String BUYER_TOS   = "구매회원 약관";
    public static final String SELLER_TOS  = "판매회원 약관";
    public static final String FINANCE_TOS = "전자금융거래 약관";
    public static final String LOCATION_TOS= "위치정보 이용약관";
    public static final String PRIVACY_TOS = "개인정보 처리방침";

    private final PolicyRepository policyRepository;

    @Transactional(readOnly = true)
    public String getBody(String key) {
        return policyRepository.findByName(key)
                .map(Terms::getTBody)
                .orElse("");
    }

    @Transactional
    public void saveBody(String key, String body) {
        Terms terms = policyRepository.findByName(key)
                .orElse(new Terms(key, body));
        terms.setTBody(body);
        policyRepository.save(terms);
    }
}
