// src/main/java/kr/co/team3/security/CustomOAuth2UserService.java
package kr.co.team3.security;

import kr.co.team3.product_entity.MemberEntity;
import kr.co.team3.product_service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    
    private final MemberService memberService;
    
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);
        
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        
        OAuth2UserInfo oauth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId, oauth2User.getAttributes());
        
        // 소셜 로그인 사용자 정보로 회원가입 또는 로그인 처리
        MemberEntity member = processOAuth2User(oauth2UserInfo);
        
        return new CustomOAuth2User(oauth2User.getAttributes(), oauth2UserInfo);
    }
    
    private MemberEntity processOAuth2User(OAuth2UserInfo oauth2UserInfo) {
        // 소셜 로그인 사용자 정보로 회원가입 또는 로그인 처리 로직
        return memberService.findByEmail(oauth2UserInfo.getEmail())
                .orElseGet(() -> memberService.register(oauth2UserInfo));
    }
}