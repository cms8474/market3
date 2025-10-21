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

            String orig = file.getOriginalFilename();
            if (orig == null || orig.isBlank()) {
                throw new IllegalArgumentException("파일명이 비어 있습니다.");
            }

            orig = Path.of(orig).getFileName().toString();

            String ext = "";
            int dot = orig.lastIndexOf('.');
            String base = (dot > 0) ? orig.substring(0, dot) : orig;
            ext = (dot > 0) ? orig.substring(dot) : "";

            base = base.replaceAll("[^a-zA-Z0-9._-가-힣\\s]", "").trim().replaceAll("\\s+", "_");
            if (base.isBlank()) base = "file";

            String candidate = base + ext;
            Path target = root.resolve(candidate);
            int idx = 1;
            while (Files.exists(target)) {
                candidate = base + "-" + idx + ext;
                target = root.resolve(candidate);
                idx++;
            }
            file.transferTo(target);
            stored = candidate;
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
        return saved;
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