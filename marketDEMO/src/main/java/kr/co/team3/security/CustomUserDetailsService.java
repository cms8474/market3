// src/main/java/kr/co/team3/security/CustomUserDetailsService.java
package kr.co.team3.security;

import kr.co.team3.product_entity.MemberEntity;
import kr.co.team3.product_service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    
    private final MemberService memberService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberEntity member = memberService.getMember(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username));

        return User.builder()
                .username(member.getUId()) // 사용자 ID를 username으로 설정
                .password(member.getUPw()) // 평문 비밀번호 그대로 사용
                .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")))
                .build();
    }
    
    // 사용자 이름을 가져오는 메서드 추가
    public String getUserName(String username) {
        return memberService.getMember(username)
                .map(MemberEntity::getUName)
                .orElse("사용자");
    }
}