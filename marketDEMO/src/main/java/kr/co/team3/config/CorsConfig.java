package kr.co.team3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/admin/**") // 상품등록 포함한 경로
                .allowedOrigins(
                        "http://localhost:8080",
                        "http://127.0.0.1:8080",
                        "http://43.201.9.146:8080"  // EC2 자신 허용
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true);
    }
}
