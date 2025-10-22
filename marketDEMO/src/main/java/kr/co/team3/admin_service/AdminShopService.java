package kr.co.team3.admin_service;

import kr.co.team3.admin_dto.PageRequestDTO;
import kr.co.team3.admin_dto.PageResponseDTO;
import kr.co.team3.admin_mapper.AdminShopMapper;
import kr.co.team3.product_dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // ★
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminShopService {

    private final AdminShopMapper adminShopMapper;
    private final PasswordEncoder passwordEncoder; // 주입은 유지(다른 곳에서 쓸 수도 있으니)

    // ★ 로컬 BCrypt 인코더 + 유틸
    private static final BCryptPasswordEncoder BCRYPT = new BCryptPasswordEncoder();

    private static boolean isBCrypt(String s) {
        return s != null && (s.startsWith("$2a$") || s.startsWith("$2b$") || s.startsWith("$2y$"));
    }
    private static String ensureBCrypt(String rawOrHash) {
        return isBCrypt(rawOrHash) ? rawOrHash : BCRYPT.encode(rawOrHash);
    }

    /** 상점(판매자) 목록 + 페이징 */
    public PageResponseDTO<MemberDTO> selectAll(PageRequestDTO pageRequestDTO) {
        log.info(">>> [Shop] selectAll() called. pg={}, size={}, type={}, keyword={}",
                pageRequestDTO.getPg(), pageRequestDTO.getSize(),
                pageRequestDTO.getSearchType(), pageRequestDTO.getKeyword());

        List<MemberDTO> dtoList = adminShopMapper.selectShopList(pageRequestDTO);

        if (dtoList == null) {
            dtoList = Collections.emptyList();
        } else {
            long nullCount = dtoList.stream().filter(Objects::isNull).count();
            if (nullCount > 0) {
                dtoList = dtoList.stream().filter(Objects::nonNull).collect(Collectors.toList());
            }
        }

        int total = adminShopMapper.selectShopTotal(pageRequestDTO);
        return new PageResponseDTO<>(pageRequestDTO, dtoList, total);
    }

    @Transactional
    public void registerShop(MemberDTO dto) {
        // ---- 0) 기본 검증/정리 ----
        if (dto == null) throw new IllegalArgumentException("등록 데이터가 없습니다.");
        if (isBlank(dto.getU_id()))   throw new IllegalArgumentException("아이디는 필수입니다.");
        if (isBlank(dto.getU_pw()))   throw new IllegalArgumentException("비밀번호는 필수입니다.");
        if (dto.getU_pw().length() < 8) throw new IllegalArgumentException("비밀번호는 8자 이상이어야 합니다.");
        if (isBlank(dto.getU_name())) throw new IllegalArgumentException("대표자명은 필수입니다.");
        if (isBlank(dto.getS_company_name())) throw new IllegalArgumentException("상호명은 필수입니다.");

        // 트림
        dto.setU_id(dto.getU_id().trim());
        dto.setU_name(dto.getU_name().trim());
        if (dto.getS_company_name() != null) dto.setS_company_name(dto.getS_company_name().trim());
        if (dto.getS_seller_no() != null) dto.setS_seller_no(dto.getS_seller_no().trim());
        if (dto.getS_sales_reg_num() != null) dto.setS_sales_reg_num(dto.getS_sales_reg_num().trim());

        // ---- ★ 비번 해시 (여기서 강제 BCrypt) ----
        dto.setU_pw(ensureBCrypt(dto.getU_pw()));

        // ---- 1) 중복 체크 ----
        if (adminShopMapper.existsUserId(dto.getU_id()) > 0) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }
        if (!isBlank(dto.getS_seller_no()) && adminShopMapper.existsSellerNo(dto.getS_seller_no()) > 0) {
            throw new IllegalArgumentException("이미 등록된 사업자등록번호입니다.");
        }
        if (!isBlank(dto.getS_sales_reg_num()) && adminShopMapper.existsSalesRegNum(dto.getS_sales_reg_num()) > 0) {
            throw new IllegalArgumentException("이미 등록된 통신판매업번호입니다.");
        }

        // ---- 2) U_USER INSERT ----
        int u = adminShopMapper.insertUserForSeller(dto);
        if (u != 1) throw new IllegalStateException("회원 등록 실패");

        // ---- 3) SELLER_INFO INSERT ----
        int s = adminShopMapper.insertSellerInfo(dto);
        if (s != 1) throw new IllegalStateException("상점 등록 실패");

        log.info(">>> shop registered: u_id={}, company={}", dto.getU_id(), dto.getS_company_name());
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    /** 상점 삭제 */
    @Transactional
    public int deleteShops(List<String> ids) {
        if (ids == null || ids.isEmpty()) return 0;
        int child = adminShopMapper.deleteSellerInfos(ids);
        int parent = adminShopMapper.deleteUsers(ids);
        return parent;
    }

    /** 상점 상태 전환 */
    public boolean approve(String uId) { return adminShopMapper.approveSeller(uId) > 0; }
    public boolean stop(String uId)    { return adminShopMapper.stopSeller(uId) > 0; }
    public boolean resume(String uId)  { return adminShopMapper.resumeSeller(uId) > 0; }
}
