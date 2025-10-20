package kr.co.team3.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * 이미지 실제 저장 위치.
     *    EC2:       file:/data/market/images/
     *    로컬:        file:///C:/data/market/images/
     *
     */
    @Value("${app.image-dir:file:/data/kmarket/images/}")
    private String imageDir;

    @Value("${app.image-url-prefix:/images/}")
    private String imageUrlPrefix;

    @Value("${app.upload-dir}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 반드시 locations 끝에 슬래시 / 가 있어야 디렉터리로 인식합니다.
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:" + (uploadDir.endsWith("/") ? uploadDir : uploadDir + "/"));
    }
}
