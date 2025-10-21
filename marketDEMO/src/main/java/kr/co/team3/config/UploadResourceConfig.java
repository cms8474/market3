package kr.co.team3.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

@Configuration
public class UploadResourceConfig implements WebMvcConfigurer {

    @Value("${app.upload-dir}")
    private String uploadDir; // /home/ec2-user/marketDEMO/uploads

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String fileLocation = Paths.get(uploadDir).toAbsolutePath().normalize().toUri().toString();
        registry.addResourceHandler("/uploads/**") // URL
                .addResourceLocations(fileLocation + "product/") // 실제 디렉터리
                .setCacheControl(CacheControl.maxAge(30, TimeUnit.DAYS).cachePublic());
    }
}
