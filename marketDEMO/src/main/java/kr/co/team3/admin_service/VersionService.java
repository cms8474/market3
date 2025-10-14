package kr.co.team3.admin_service;

import jakarta.transaction.Transactional;
import kr.co.team3.admin_entity.Version;
import kr.co.team3.admin_repository.VersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VersionService {
    private final VersionRepository versionRepository;

    private Version ensure() {
        Version v = versionRepository.findTopByOrderByIdDesc();
        if (v == null) {
            v = versionRepository.save(Version.builder().title("").subTitle("").build());
        }
        return v;
    }
    private String n(String s){ return s == null ? "" : s; }

    // 제일 위에꺼 조회
    public Version getLatestVersion() {
        return versionRepository.findTopByOrderByIdDesc();
    }

    @Transactional
    public void updateSite(String title, String subTitle) {
        var v = ensure();
        v.setTitle(n(title));
        v.setSubTitle(n(subTitle));
    }
    @Transactional
    public void updateCompany(String company, String ceo, String sellerRegNo,
                              String onlineSalesRegNo, String addr) {
        var v = ensure();
        v.setCompany(n(company));
        v.setCeo(n(ceo));
        v.setSellerRegNo(n(sellerRegNo));
        v.setOnlineSalesRegNo(n(onlineSalesRegNo));
        v.setAddr(n(addr));
    }

    @Transactional
    public void updateSupport(String tel, String actTime, String email, String managerPhone) {
        var v = ensure();
        v.setTel(n(tel));
        v.setActTime(n(actTime));
        v.setEmail(n(email));
        v.setManagerPhone(n(managerPhone));
    }

    @Transactional
    public void updateCopyright(String copylight) {
        var v = ensure();
        v.setCopylight(n(copylight));
    }


}
