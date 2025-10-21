package kr.co.team3.admin_service;

import jakarta.transaction.Transactional;
import kr.co.team3.admin_dto.ProductDetailDTO;
import kr.co.team3.admin_dto.ProductItemDTO;
import kr.co.team3.admin_dto.ProductOptionDTO;
import kr.co.team3.admin_mapper.AdminProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminProductService {

    private final AdminProductMapper mapper;

    // 업로드 루트 경로 설정 (application.properties에서 주입)
    @Value("${app.upload-dir:uploads}")
    private String uploadRoot;

    /**
     * 상품 등록
     */
    @Transactional
    public String register(ProductItemDTO item,
                           ProductDetailDTO detail,
                           List<ProductOptionDTO> options,
                           MultipartFile listImage,
                           MultipartFile mainImage,
                           MultipartFile detailImage,
                           MultipartFile infoImage) {

        // 1) 파일 저장 → 상대경로(DB에 저장될 경로) 셋팅
        item.setImageList(store(listImage, "list"));
        item.setImageMain(store(mainImage, "main"));
        item.setImageDetail(store(detailImage, "detail"));
        item.setDetailInfo(store(infoImage, "info")); // null 가능

        // 2) PRODUCT_ITEM 저장
        mapper.insertProductItem(item);

        // 3) 트리거로 생성된 P_PID 조회
        String pid = mapper.selectLastPidBySeller(item.getSellerId());
        if (!StringUtils.hasText(pid)) {
            throw new IllegalStateException("상품ID 생성 실패");
        }

        // 4) PRODUCT_DETAIL 저장
        if (detail == null) detail = new ProductDetailDTO();
        detail.setPPid(pid);
        mapper.insertProductDetail(detail);

        // 5) PRODUCT_OPTION 저장 (여러 개)
        if (options != null) {
            for (ProductOptionDTO o : options) {
                if (o == null || !StringUtils.hasText(o.getName()) || !StringUtils.hasText(o.getSelection()))
                    continue;
                o.setPPid(pid);
                if (o.getAddPrice() == null) o.setAddPrice(0);
                if (o.getStock() == null) o.setStock(999);
                mapper.insertProductOption(o);
            }
        }

        return pid;
    }

    /**
     * 파일 저장 후 웹경로(/uploads/...) 반환
     */
    private String store(MultipartFile file, String tag) {
        try {
            if (file == null || file.isEmpty()) return null;

            String yyyymm = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM"));
            // 항상 /uploads/product/ 하위에 저장
            String webDir = "/uploads/product/" + yyyymm + "/";

            // 실제 파일 저장 경로
            Path base = Paths.get(uploadRoot, "product").toAbsolutePath().normalize();
            Path dir  = base.resolve(yyyymm);
            Files.createDirectories(dir);

            String ext = "";
            String original = file.getOriginalFilename();
            if (original != null && original.lastIndexOf('.') != -1) {
                ext = original.substring(original.lastIndexOf('.'));
            }

            String filename = UUID.randomUUID() + "_" + tag + ext;
            Path dest = dir.resolve(filename);
            file.transferTo(dest.toFile());

            return webDir + filename; // DB에는 /uploads/product/... 저장
        } catch (Exception e) {
            throw new RuntimeException("파일 저장 실패(" + tag + "): " + e.getMessage(), e);
        }
    }
}
