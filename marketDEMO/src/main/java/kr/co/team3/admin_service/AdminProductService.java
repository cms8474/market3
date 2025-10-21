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

    @Value("${app.upload-dir:uploads}")
    private String uploadRoot;

    @Transactional
    public String register(ProductItemDTO item,
                           ProductDetailDTO detail,
                           List<ProductOptionDTO> options,
                           MultipartFile listImage,
                           MultipartFile mainImage,
                           MultipartFile detailImage,
                           MultipartFile infoImage) {

        // 1) 파일 저장 → 상대경로 셋팅
        item.setImageList(store(listImage, "list"));
        item.setImageMain(store(mainImage, "main"));
        item.setImageDetail(store(detailImage, "detail"));
        item.setDetailInfo(store(infoImage, "info")); // null 가능

        // 2) PRODUCT_ITEM
        mapper.insertProductItem(item);

        // 3) 트리거가 생성한 PID 확보
        String pid = mapper.selectLastPidBySeller(item.getSellerId());
        if (!StringUtils.hasText(pid)) {
            throw new IllegalStateException("상품ID 생성 실패");
        }

        // 4) PRODUCT_DETAIL
        if (detail == null) detail = new ProductDetailDTO();
        detail.setPPid(pid);
        mapper.insertProductDetail(detail);

        // 5) PRODUCT_OPTION (여러 행)
        if (options != null) {
            for (ProductOptionDTO o : options) {
                if (o == null || !StringUtils.hasText(o.getName()) || !StringUtils.hasText(o.getSelection())) continue;
                o.setPPid(pid);
                if (o.getAddPrice() == null) o.setAddPrice(0);
                if (o.getStock() == null) o.setStock(999);
                mapper.insertProductOption(o);
            }
        }

        return pid;
    }

    /** 파일 저장 후 웹서빙용 상대경로(/uploads/...) 반환 */
    private String store(MultipartFile file, String tag) {
        try {
            if (file == null || file.isEmpty()) return null;
            String yyyymm = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM"));
            String relDir = "/uploads/product/" + yyyymm + "/";

            String ext = "";
            String original = file.getOriginalFilename();
            if (original != null && original.contains(".")) {
                ext = original.substring(original.lastIndexOf('.'));
            }
            String filename = UUID.randomUUID() + "_" + tag + ext;

            Path dir = Paths.get(uploadRoot + relDir.replace('/', java.io.File.separatorChar));
            Files.createDirectories(dir);
            file.transferTo(dir.resolve(filename).toFile());

            return relDir + filename;
        } catch (Exception e) {
            throw new RuntimeException("파일 저장 실패(" + tag + "): " + e.getMessage(), e);
        }
    }
}
