package kr.co.team3.admin_service;

import jakarta.transaction.Transactional;
import kr.co.team3.admin_entity.Version;
import kr.co.team3.admin_repository.VersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class VersionService {
    private final VersionRepository versionRepository;

    public VersionService(VersionRepository versionRepository) {
        this.versionRepository = versionRepository;
    }
    // 제일 위에꺼 조회
    public Version getLatestVersion() {
        return versionRepository.findTopByOrderByIdDesc().orElse(null);
    }
    private Version baseOrEmpty() {
        return Optional.ofNullable(getLatestVersion()).orElse(Version.builder().build());
    }

    // 사이트 타이틀/서브타이틀만 변경하여 "새 버전" INSERT
    public void updateSite(String siteTitle, String siteSubtitle) {
        Version next = baseOrEmpty().copy();
        next.setTitle(siteTitle);
        next.setSubTitle(siteSubtitle);
        next.setId(null);          // ★ 새 레코드 INSERT
        versionRepository.save(next);
    }
    public void updateLogoFavicon(String header, String footer, String fav) {
        Version next = baseOrEmpty().copy();
        if (header != null) next.setHeaderLogo(header);
        if (footer != null) next.setFooterLogo(footer);
        if (fav != null)    next.setFavicon(fav);
        next.setId(null);
        versionRepository.save(next);
    }

    public void updateCompany(String companyName, String ceoName, String bizRegNo,
                              String mailOrderNo, String fullAddr) {
        Version next = baseOrEmpty().copy();
        next.setCompany(companyName);
        next.setCeo(ceoName);
        next.setSellerRegNo(bizRegNo);
        next.setOnlineSalesRegNo(mailOrderNo);
        next.setAddr(fullAddr);
        next.setId(null);
        versionRepository.save(next);
    }

    public void updateSupport(String csTel, String csHours, String csEmail, String disputeTel) {
        Version next = baseOrEmpty().copy();
        next.setTel(csTel);
        next.setActTime(csHours);
        next.setEmail(csEmail);
        next.setManagerPhone(disputeTel);
        next.setId(null);
        versionRepository.save(next);
    }
    public void updateCopyright(String copyright) {
        Version next = baseOrEmpty().copy();
        next.setCopylight(copyright);
        next.setId(null);
        versionRepository.save(next);
    }


}
