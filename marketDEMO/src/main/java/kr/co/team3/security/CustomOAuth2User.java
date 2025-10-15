// src/main/java/kr/co/team3/security/CustomOAuth2User.java
package kr.co.team3.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {

    private final Map<String, Object> attributes;
    private final OAuth2UserInfo oauth2UserInfo;

    public CustomOAuth2User(Map<String, Object> attributes, OAuth2UserInfo oauth2UserInfo) {
        this.attributes = attributes;
        this.oauth2UserInfo = oauth2UserInfo;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null; // 필요시 구현
    }

    @Override
    public String getName() {
        return oauth2UserInfo.getName();
    }

    public String getEmail() {
        return oauth2UserInfo.getEmail();
    }

    public String getId() {
        return oauth2UserInfo.getId();
    }
}