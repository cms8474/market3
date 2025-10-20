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
        String fileLocation = java.nio.file.Paths.get(uploadDir).toAbsolutePath().toUri().toString();
        registry.addResourceHandler("/images/**")
                .addResourceLocations(
                        "classpath:/static/images/", // JAR 안의 /static/images
                        fileLocation                  // 외부 폴더
                )
                .setCachePeriod(3600)
                .resourceChain(true)
                .addResolver(new org.springframework.web.servlet.resource.PathResourceResolver());
    }
}
