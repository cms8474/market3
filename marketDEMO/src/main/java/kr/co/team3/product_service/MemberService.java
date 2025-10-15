package kr.co.team3.product_service;

import kr.co.team3.product_entity.MemberEntity;
import kr.co.team3.product_repository.MemberRepository;
import kr.co.team3.security.OAuth2UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // ✅ 로그인 - 평문 비밀번호 검증 (임시)
    public Optional<MemberEntity> login(String uId, String uPw) {
        return Optional.ofNullable(memberRepository.findByuIdAnduPw(uId, uPw));
    }

    // ✅ 회원가입 - 평문 비밀번호 저장 (임시)
    public MemberEntity register(MemberEntity member) {
        return memberRepository.save(member);
    }

    // ✅ 이메일 중복 검사
    public boolean existsByMail(String uMail) {
        return memberRepository.findByuMail(uMail) != null;
    }

    // ✅ 비밀번호 변경
    public void changePassword(String uId, String newPw) {
        memberRepository.findById(uId).ifPresent(member -> {
            member.setUPw(newPw);
            memberRepository.save(member);
        });
    }

    // ✅ 회원 단일 조회
    public Optional<MemberEntity> getMember(String uId) {
        return memberRepository.findById(uId);
    }


    // ✅ 소셜 로그인용 이메일로 회원 찾기
    public Optional<MemberEntity> findByEmail(String email) {
        return Optional.ofNullable(memberRepository.findByuMail(email));
    }

    // ✅ 소셜 로그인용 회원가입 - 비밀번호 없음
    public MemberEntity register(OAuth2UserInfo oauth2UserInfo) {
        MemberEntity member = MemberEntity.builder()
                .uId(oauth2UserInfo.getId())
                .uName(oauth2UserInfo.getName())
                .uMail(oauth2UserInfo.getEmail())
                .uType("SOCIAL")
                .uStatus("ACTIVE")
                .uCreateDay(LocalDateTime.now())
                .uPoint(0)
                .uRank("BRONZE")
                .uPw("") // 소셜 로그인은 비밀번호 없음
                .build();
        
        return memberRepository.save(member);
    }

    // ✅ 비밀번호 암호화 (임시 비활성화)
    public void updatePasswordToEncrypted() {
        // 임시로 비활성화 - 나중에 별도 서비스로 구현
        System.out.println("비밀번호 암호화 기능이 임시로 비활성화되었습니다.");
    }

    
}
