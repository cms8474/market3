package kr.co.team3.admin_service;

import jakarta.transaction.Transactional;
import kr.co.team3.admin_dto.ProductDetailDTO;
import kr.co.team3.admin_dto.ProductItemDTO;
import kr.co.team3.admin_dto.ProductOptionDTO;
import kr.co.team3.admin_mapper.AdminProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminProductService {

    private final AdminProductMapper mapper;

    // application.properties: app.upload-dir=/home/ec2-user/uploads
    @Value("${app.upload-dir:uploads}")
    private String uploadRoot;

    /**
     * 기존 등록 메서드(호출부 호환 유지용)
     * - 오버로드된 store(file, tag) → pcId "unknown"으로 저장
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
        item.setImageList(store(listImage, "list"));      // <-- 오버로드 사용
        item.setImageMain(store(mainImage, "main"));
        item.setImageDetail(store(detailImage, "detail"));
        item.setDetailInfo(store(infoImage, "info"));     // null 가능

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
                if (o == null || !StringUtils.hasText(o.getName()) || !StringUtils.hasText(o.getSelection())) continue;
                o.setPPid(pid);
                if (o.getAddPrice() == null) o.setAddPrice(0);
                if (o.getStock() == null) o.setStock(999);
                mapper.insertProductOption(o);
            }
        }

        return pid;
    }

    /**
     * 원본 파일명 + 카테고리별 폴더 저장 버전
     */
    @Transactional
    public String registerOriginalFileNames(ProductItemDTO item,
                                            ProductDetailDTO detail,
                                            List<ProductOptionDTO> options,
                                            MultipartFile listImage,
                                            MultipartFile mainImage,
                                            MultipartFile detailImage,
                                            MultipartFile infoImage) {

        // 파일 저장 (원본 이름 그대로, 카테고리별)
        String pcId = item.getPcId();
        item.setImageList(store(listImage, "list", pcId));
        item.setImageMain(store(mainImage, "main", pcId));
        item.setImageDetail(store(detailImage, "detail", pcId));
        item.setDetailInfo(store(infoImage, "info", pcId));

        // 상품 등록
        mapper.insertProductItem(item);

        String pid = mapper.selectLastPidBySeller(item.getSellerId());
        if (!StringUtils.hasText(pid)) throw new IllegalStateException("상품ID 생성 실패");

        if (detail == null) detail = new ProductDetailDTO();
        detail.setPPid(pid);
        mapper.insertProductDetail(detail);

        if (options != null) {
            for (ProductOptionDTO o : options) {
                if (!StringUtils.hasText(o.getName()) || !StringUtils.hasText(o.getSelection())) continue;
                o.setPPid(pid);
                if (o.getAddPrice() == null) o.setAddPrice(0);
                if (o.getStock() == null) o.setStock(999);
                mapper.insertProductOption(o);
            }
        }
        return pid;
    }

    /* ================== 파일 저장 유틸 ================== */

    //  호환용 오버로드: pcId 미전달 시 "unknown" 폴더
    private String store(MultipartFile file, String tag) {
        return store(file, tag, "unknown");
    }

    /**
     * 카테고리별 디렉터리(/uploads/product/{pcId}/)에 원본 파일명 그대로 저장
     * 반환값: 웹 경로(/uploads/...) — 템플릿에서 그대로 th:src 가능
     */
    private String store(MultipartFile file, String tag, String pcId) {
        try {
            if (file == null || file.isEmpty()) return null;

            // pcId 폴더명 안전화
            String safePcId = (pcId == null) ? "unknown" : pcId.replaceAll("[^a-zA-Z0-9_\\-]", "_");

            // 베이스/디렉터리 생성
            String webDir = "/uploads/product/" + safePcId + "/";
            Path base = Paths.get(uploadRoot, "product").toAbsolutePath().normalize();
            Path dir  = base.resolve(safePcId);
            Files.createDirectories(dir);

            // 원본 파일명 안전화: 경로 분리, 위험문자 제거 최소화
            String original = file.getOriginalFilename();
            if (!StringUtils.hasText(original)) return null;
            // 경로 스트립
            String baseName = Paths.get(original).getFileName().toString();
            // (선택) 위험문자 아주 기본적으로만 정리
            baseName = baseName.replaceAll("[\\r\\n]", "_");

            Path dest = dir.resolve(baseName);
            file.transferTo(dest.toFile());

            return webDir + baseName;
        } catch (Exception e) {
            log.error("파일 저장 실패(tag={}, pcId={}): {}", tag, pcId, e.getMessage(), e);
            throw new RuntimeException("파일 저장 실패(" + tag + "): " + e.getMessage(), e);
        }
    }

    /* 조회용 */
    public ProductItemDTO getItem(String pid)   { return mapper.selectProductByPid(pid); }
    public ProductDetailDTO getDetail(String pid){ return mapper.selectDetailByPid(pid); }
    public List<ProductOptionDTO> getOptions(String pid){ return mapper.selectOptionsByPid(pid); }

    /**
     * 수정: 이미지 파일은 "새 파일 업로드 시 교체", 아니면 기존 경로 유지
     * 카테고리 변경해도 기존 이미지 경로는 그대로 두고, 새로 업로드된 파일만 새 카테고리 폴더 사용
     */
    @Transactional
    public void updateProduct(ProductItemDTO item,
                              ProductDetailDTO detail,
                              List<ProductOptionDTO> options,
                              MultipartFile listImage,
                              MultipartFile mainImage,
                              MultipartFile detailImage,
                              MultipartFile infoImage) {

        // 현재 DB 값 조회 (이미지 경로 유지용)
        ProductItemDTO current = mapper.selectProductByPid(item.getPPid());
        if (current == null) throw new IllegalArgumentException("대상 상품이 없습니다: " + item.getPPid());

        String pcIdForNewFiles = item.getPcId() != null ? item.getPcId() : current.getPcId();

        // 이미지 교체(있으면 저장하고 교체, 없으면 기존 유지)
        String imgList   = (listImage   != null && !listImage.isEmpty())
                ? store(listImage, "list",   pcIdForNewFiles) : current.getImageList();
        String imgMain   = (mainImage   != null && !mainImage.isEmpty())
                ? store(mainImage, "main",   pcIdForNewFiles) : current.getImageMain();
        String imgDetail = (detailImage != null && !detailImage.isEmpty())
                ? store(detailImage, "detail", pcIdForNewFiles) : current.getImageDetail();
        String imgInfo   = (infoImage   != null && !infoImage.isEmpty())
                ? store(infoImage, "info",   pcIdForNewFiles) : current.getDetailInfo();

        item.setImageList(imgList);
        item.setImageMain(imgMain);
        item.setImageDetail(imgDetail);
        item.setDetailInfo(imgInfo);

        // ITEM / DETAIL 업데이트
        mapper.updateProductItem(item);
        mapper.upsertProductDetail(detail); // 있으면 UPDATE, 없으면 INSERT

        // 옵션은 간단히 전체 삭제 후 재삽입(운영 중이면 diff 적용도 가능)
        mapper.deleteOptionsByPid(item.getPPid());
        if (options != null) {
            for (ProductOptionDTO o : options) {
                if (!StringUtils.hasText(o.getName()) || !StringUtils.hasText(o.getSelection())) continue;
                if (o.getAddPrice() == null) o.setAddPrice(0);
                if (o.getStock() == null) o.setStock(999);
                o.setPPid(item.getPPid());
                mapper.insertProductOption(o);
            }
        }
    }



}
