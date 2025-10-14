package kr.co.team3.product_service;

import kr.co.team3.product_entity.MemberEntity;
import kr.co.team3.product_repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // ✅ 로그인
    public Optional<MemberEntity> login(String uId, String uPw) {
        return Optional.ofNullable(memberRepository.findByuIdAnduPw(uId, uPw));
    }

    // ✅ 회원가입
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
}
