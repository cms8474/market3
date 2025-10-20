package kr.co.team3.admin_service;

import jakarta.transaction.Transactional;
import kr.co.team3.admin_entity.Banner;
import kr.co.team3.admin_repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BannerService {

    private final BannerRepository bannerRepository;
    private final Path uploadRoot = Path.of("src/main/resources/static/images");
    public List<Banner> list(String location) {
        if (location == null || location.isBlank()) {
            return bannerRepository.findAllByOrderByANoDesc();
        }
        return bannerRepository.findByALocationOrderByANoDesc(location);
    }

    @Transactional
    public Banner create(String name, String size, String backColor, String link,
                         String location,
                         LocalDate startDate, LocalDate endDate,
                         LocalDateTime startTime, LocalDateTime endTime,
                         MultipartFile file) throws Exception {

        String stored = null;
        if (file != null && !file.isEmpty()) {
            Files.createDirectories(uploadRoot);
            String ext = "";
            String orig = file.getOriginalFilename();
            if (orig != null && orig.contains(".")) {
                ext = orig.substring(orig.lastIndexOf("."));
            }
            stored = UUID.randomUUID() + ext;
            file.transferTo(uploadRoot.resolve(stored).toFile());
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

        return bannerRepository.save(ad);
    }

    @Transactional
    public void deleteByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) return;
        bannerRepository.deleteAllById(ids);
    }
}