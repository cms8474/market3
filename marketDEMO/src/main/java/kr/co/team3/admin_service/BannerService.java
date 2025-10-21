package kr.co.team3.admin_service;

import jakarta.transaction.Transactional;
import kr.co.team3.admin_entity.Banner;
import kr.co.team3.admin_repository.BannerRepository;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BannerService {

    private final BannerRepository bannerRepository;
//    private final Path uploadRoot = Path.of("market3/marketDEMO/v_rear/src/main/resources/static/images/");
//    public List<Banner> list(String location) {
//        if (location == null || location.isBlank()) {
//            return bannerRepository.findAllByOrderByANoDesc();
//        }
//        return bannerRepository.findByALocationOrderByANoDesc(location);
//    }
    @Value("${app.upload-dir}")
    private String uploadDir;

    @Transactional
    public Banner create(String name, String size, String backColor, String link,
                         String location,
                         LocalDate startDate, LocalDate endDate,
                         LocalDateTime startTime, LocalDateTime endTime,
                         MultipartFile file) throws Exception {
        log.info("[BANNER] create start, uploadDir={}", uploadDir);
        String stored = null;

        if (file != null && !file.isEmpty()) {
            Path root = Path.of(uploadDir).toAbsolutePath().normalize();
            log.info("[BANNER] will save file: orig={}, size={}", file.getOriginalFilename(), file.getSize());
            Files.createDirectories(root); // 외부 디렉터리 생성

            String ext = "";
            String orig = file.getOriginalFilename();
            if (orig != null && orig.contains(".")) {
                ext = orig.substring(orig.lastIndexOf("."));
            }
            stored = UUID.randomUUID() + ext;

            Path target = root.resolve(stored);
            try (var in = file.getInputStream()) {
                Files.copy(in, root.resolve(stored)); // 이미 있으면 실패 -> 필요시 REPLACE 옵션 추가
            }

            file.transferTo(root.resolve(stored).toFile());
            log.info("[BANNER] file saved as {}", stored);
        }

        Banner ad = Banner.builder()
                .aName(name)
                .aSize(size)
                .aBackColor(backColor)
                .aLink(link)
                .aLocation(location)
                .aStartDate(startDate)
                .aEndDate(endDate)
                .aStartTime(startTime)
                .aEndTime(endTime)
                .aFile(stored)
                .build();

        Banner saved = bannerRepository.save(ad);
        log.info("[BANNER] JPA saved id={}", saved.getANo());
        return bannerRepository.save(ad);
    }

    @Transactional
    public void deleteByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) return;

        List<Banner> targets = bannerRepository.findAllById(ids);
        for (Banner b : targets) {
            if (b.getAFile() != null) {
                try {
                    Files.deleteIfExists(Path.of(uploadDir).resolve(b.getAFile()));
                } catch (Exception ignore) {}
            }
        }
        bannerRepository.deleteAllById(ids);
    }

    @Transactional
    public List<Banner> list() {
        return bannerRepository.findAllByOrderByANoDesc();
    }
}