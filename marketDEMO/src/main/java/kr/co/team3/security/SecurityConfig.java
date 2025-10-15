// src/main/java/kr/co/team3/security/SecurityConfig.java
package kr.co.team3.security;

import kr.co.team3.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // 인가 설정
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/member/join", "/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/member/login").anonymous() // 로그인하지 않은 사용자만 접근 가능
                .requestMatchers("/my/**").authenticated() // 로그인한 사용자만 접근 가능
                .requestMatchers("/manager/**").hasAnyRole("ADMIN", "MANAGER")
                .requestMatchers("/admin/**").permitAll()
                .requestMatchers("/article/**").hasAnyRole("ADMIN", "MANAGER", "MEMBER")
                .anyRequest().permitAll()
            )
            
            // 로그인 설정
            .formLogin(form -> form
                .loginPage("/member/login")
                .loginProcessingUrl("/member/login")
                .defaultSuccessUrl("/")
                .failureUrl("/member/login?error=true")
                .usernameParameter("uId")
                .passwordParameter("uPw")
                .permitAll()
            )
            
            // 로그아웃 설정
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me")
                .clearAuthentication(true)
                .permitAll()
            )
            
            // Remember Me 설정
            .rememberMe(remember -> remember
                .rememberMeParameter("rememberMe")
                .tokenValiditySeconds(7 * 24 * 60 * 60) // 7일
                .userDetailsService(userDetailsService)
                .key("remember-me-key")
                .alwaysRemember(false) // 명시적으로 false 설정
            )
            
            // CSRF 설정
            .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString(); // 평문 그대로 반환
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                // 평문 비밀번호 직접 비교
                return rawPassword.toString().equals(encodedPassword);
            }
        };
    }

}