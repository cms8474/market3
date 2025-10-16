package kr.co.team3.product_service;

import kr.co.team3.product_entity.MemberEntity;
import kr.co.team3.product_entity.SellerInfoEntity;
import kr.co.team3.product_mapper.MemberMapper;
import kr.co.team3.security.OAuth2UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final EmailService emailService;

    // ✅ 로그인 - 평문 비밀번호 검증 (임시)
    public Optional<MemberEntity> login(String uId, String uPw) {
        Map<String, String> params = new HashMap<>();
        params.put("uId", uId);
        params.put("uPw", uPw);
        return Optional.ofNullable(memberMapper.findByuIdAnduPw(params));
    }
    
    /** ✅ 아이디 중복 검사 */
    public boolean existsById(String uId) {
        return memberMapper.countById(uId) > 0;
    }

    // ✅ 회원가입 - BCrypt 접두사 방식으로 암호화 구분
    public MemberEntity register(MemberEntity member) {
        try {
            System.out.println("=== 회원가입 디버그 ===");
            System.out.println("U_ID: " + member.getUId());
            System.out.println("U_PW (원본): " + member.getUPw());
            System.out.println("U_NAME: " + member.getUName());
            System.out.println("U_BIRTH: " + member.getUBirth());
            System.out.println("U_GENDER: " + member.getUGender());
            System.out.println("U_MAIL: " + member.getUMail());
            System.out.println("U_PHONE: " + member.getUPhone());
            System.out.println("U_POSTAL: " + member.getUPostal());
            System.out.println("U_BASE_ADDR: " + member.getUBaseAddr());
            System.out.println("U_DETAIL_ADDR: " + member.getUDetailAddr());
            System.out.println("U_TYPE: " + member.getUType());
            System.out.println("U_POINT: " + member.getUPoint());
            System.out.println("U_RANK: " + member.getURank());
            System.out.println("U_STATUS: " + member.getUStatus());
            System.out.println("U_CREATE_DAY: " + member.getUCreateDay());
            
            // MyBatis 동작 테스트 - COUNT 쿼리 먼저 실행
            System.out.println("=== MyBatis 동작 테스트 시작 ===");
            try {
                int existingCount = memberMapper.countById(member.getUId());
                System.out.println("기존 회원 수 조회 성공: " + existingCount);
            } catch (Exception e) {
                System.out.println("MyBatis COUNT 쿼리 오류: " + e.getMessage());
                e.printStackTrace();
                throw e;
            }
            System.out.println("=== MyBatis 동작 테스트 완료 ===");
            
            // U_ID가 user, seller, admin으로 시작하는 경우 평문 저장
            if (member.getUId().startsWith("user") || 
                member.getUId().startsWith("seller") || 
                member.getUId().startsWith("admin")) {
                System.out.println("평문 저장 선택됨");
                System.out.println("MyBatis insertMember() 호출 시작");
                try {
                    memberMapper.insertMember(member);
                    System.out.println("MyBatis insertMember() 호출 완료");
                    System.out.println("평문 저장 완료: " + member.getUId());
                    return member;
                } catch (Exception e) {
                    System.out.println("MyBatis insertMember() 오류: " + e.getMessage());
                    e.printStackTrace();
                    throw e;
                }
            } else {
                System.out.println("BCrypt 암호화 선택됨");
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                String encodedPw = encoder.encode(member.getUPw());
                System.out.println("U_PW (암호화): " + encodedPw);
                member.setUPw(encodedPw);
                System.out.println("MyBatis insertMember() 호출 시작");
                System.out.println("INSERT 파라미터 확인:");
                System.out.println("  U_ID: " + member.getUId());
                System.out.println("  U_PW: " + member.getUPw());
                System.out.println("  U_NAME: " + member.getUName());
                System.out.println("  U_BIRTH: " + member.getUBirth());
                System.out.println("  U_GENDER: " + member.getUGender());
                System.out.println("  U_MAIL: " + member.getUMail());
                System.out.println("  U_PHONE: " + member.getUPhone());
                System.out.println("  U_POSTAL: " + member.getUPostal());
                System.out.println("  U_BASE_ADDR: " + member.getUBaseAddr());
                System.out.println("  U_DETAIL_ADDR: " + member.getUDetailAddr());
                System.out.println("  U_TYPE: " + member.getUType());
                System.out.println("  U_POINT: " + member.getUPoint());
                System.out.println("  U_RANK: " + member.getURank());
                System.out.println("  U_STATUS: " + member.getUStatus());
                
                try {
                    memberMapper.insertMember(member);
                    System.out.println("MyBatis insertMember() 호출 완료");
                    System.out.println("BCrypt 저장 완료: " + member.getUId());
                    return member;
                } catch (Exception e) {
                    System.out.println("=== MyBatis insertMember() 오류 상세 ===");
                    System.out.println("오류 메시지: " + e.getMessage());
                    System.out.println("오류 클래스: " + e.getClass().getName());
                    System.out.println("오류 원인: " + (e.getCause() != null ? e.getCause().getMessage() : "없음"));
                    e.printStackTrace();
                    throw e;
                }
            }
        } catch (Exception e) {
            System.out.println("회원가입 서비스 오류: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    // ✅ 이메일 중복 검사
    public boolean existsByMail(String uMail) {
        return memberMapper.countByMail(uMail) > 0;
    }








    // ✅ 비밀번호 변경 (MyBatis로 구현 필요)
    public void changePassword(String uId, String newPw) {
        // TODO: MyBatis UPDATE 쿼리 구현
        System.out.println("비밀번호 변경 기능은 MyBatis UPDATE 쿼리로 구현 필요");
    }

    // ✅ 회원 단일 조회 (MyBatis로 구현)
    public Optional<MemberEntity> getMember(String uId) {
        try {
            System.out.println("=== 회원 조회 시작 ===");
            System.out.println("조회할 U_ID: " + uId);
            MemberEntity member = memberMapper.findByuId(uId);
            System.out.println("조회 결과: " + (member != null ? "찾음" : "없음"));
            if (member != null) {
                System.out.println("조회된 회원 정보:");
                System.out.println("  U_ID: " + member.getUId());
                System.out.println("  U_PW: " + member.getUPw());
                System.out.println("  U_NAME: " + member.getUName());
            }
            System.out.println("=== 회원 조회 완료 ===");
            return Optional.ofNullable(member);
        } catch (Exception e) {
            System.out.println("회원 조회 오류: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }

    // ✅ 소셜 로그인용 이메일로 회원 찾기
    public Optional<MemberEntity> findByEmail(String email) {
        return Optional.ofNullable(memberMapper.findByuMail(email));
    }

    // ✅ 소셜 로그인용 회원가입 - 비밀번호 없음
    public MemberEntity register(OAuth2UserInfo oauth2UserInfo) {
        MemberEntity member = MemberEntity.builder()
                .uId(oauth2UserInfo.getId())
                .uName(oauth2UserInfo.getName())
                .uMail(oauth2UserInfo.getEmail())
                .uType("SOCIAL")
                .uStatus("ACTIVE")
                .uCreateDay(LocalDateTime.now())
                .uPoint(0)
                .uRank("BRONZE")
                .uPw("") // 소셜 로그인은 비밀번호 없음
                .build();
        
        memberMapper.insertMember(member);
        return member;
    }

    // ✅ DB 연결 테스트
    public long getMemberCount() {
        try {
            System.out.println("=== MyBatis COUNT 쿼리 테스트 시작 ===");
            int count = memberMapper.countById("TESTER01");
            System.out.println("MyBatis COUNT 쿼리 결과: " + count);
            System.out.println("=== MyBatis COUNT 쿼리 테스트 완료 ===");
            return count;
        } catch (Exception e) {
            System.out.println("MyBatis COUNT 쿼리 오류: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    // ✅ 이메일 인증번호 발송
    public Map<String, Object> sendEmailVerificationCode(String email) {
        Map<String, Object> result = new HashMap<>();
        
        try {
            System.out.println("=== 이메일 인증번호 발송 시작 ===");
            System.out.println("발송 대상 이메일: " + email);
            
            // 6자리 랜덤 인증번호 생성
            String code = emailService.generateVerificationCode();
            System.out.println("생성된 인증번호: " + code);
            
            // 이메일 발송
            String sendResult = emailService.sendVerificationEmail(email, code);
            
            if ("SUCCESS".equals(sendResult)) {
                // 발송 성공 시 인증번호 저장
                emailVerificationCodes.put(email, code);
                System.out.println("이메일 발송 성공 및 인증번호 저장 완료");
                result.put("success", true);
                result.put("message", "인증번호가 발송되었습니다.");
                result.put("code", code);
            } else {
                System.out.println("이메일 발송 실패: " + sendResult);
                result.put("success", false);
                result.put("message", sendResult);
                result.put("code", null);
            }
            
            return result;
            
        } catch (Exception e) {
            System.out.println("이메일 인증번호 발송 중 오류: " + e.getMessage());
            e.printStackTrace();
            result.put("success", false);
            result.put("message", "이메일 발송 중 오류가 발생했습니다.");
            result.put("code", null);
            return result;
        }
    }

    // ✅ 이메일 인증번호 확인
    public boolean verifyEmailCode(String email, String inputCode) {
        String storedCode = emailVerificationCodes.get(email);
        return storedCode != null && storedCode.equals(inputCode);
    }

    // ✅ 약관 내용 조회
    public String getTermsContent(String termName) {
        try {
            System.out.println("=== 약관 조회 시작 ===");
            System.out.println("조회할 약관명: " + termName);
            
            // m_terms 테이블에서 조회
            String termsContent = memberMapper.getTermsContent(termName);
            
            if (termsContent != null && !termsContent.trim().isEmpty()) {
                System.out.println("약관 조회 성공: " + termName);
                return termsContent;
            } else {
                System.out.println("약관 조회 실패: " + termName + " - 내용이 없음");
                // 임시로 하드코딩된 내용 반환
                if ("구매회원 약관".equals(termName)) {
                    return "제1조 (목적)\n본 약관은 케이마켓(이하 \"회사\")이 제공하는 서비스의 이용조건 및 절차에 관한 기본적인 사항을 규정함을 목적으로 합니다.\n\n제2조 (회원의 정의)\n회원이라 함은 회사의 서비스에 접속하여 본 약관에 따라 이용계약을 체결하고 회사가 제공하는 서비스를 이용하는 고객을 말합니다.";
                } else if ("판매회원 약관".equals(termName)) {
                    return "제1조 (목적)\n본 약관은 케이마켓(이하 \"회사\")이 제공하는 판매자 서비스의 이용조건 및 절차에 관한 기본적인 사항을 규정함을 목적으로 합니다.\n\n제2조 (판매자의 정의)\n판매자라 함은 회사의 서비스에 접속하여 본 약관에 따라 판매자 이용계약을 체결하고 회사가 제공하는 판매자 서비스를 이용하는 고객을 말합니다.";
                } else if ("개인정보 처리방침".equals(termName)) {
                    return "제1조 (개인정보 수집 항목)\n회사는 회원가입, 상담, 서비스 신청 등을 위해 다음과 같은 개인정보를 수집할 수 있습니다.\n- 필수항목: 이름, 아이디, 비밀번호, 이메일, 휴대전화번호\n- 선택항목: 주소, 관심분야\n\n제2조 (개인정보의 수집 및 이용목적)\n수집된 개인정보는 회원 관리, 서비스 제공, 본인 확인, 마케팅 활용 등을 위해 사용됩니다.";
                }
                return "약관 내용을 찾을 수 없습니다.";
            }
        } catch (Exception e) {
            System.out.println("약관 조회 오류: " + e.getMessage());
            e.printStackTrace();
            return "약관 조회 중 오류가 발생했습니다.";
        }
    }

    // ✅ 판매자 회원가입 - U_USER와 SELLER_INFO 테이블에 동시 저장
    public MemberEntity registerSeller(MemberEntity member, SellerInfoEntity sellerInfo) {
        try {
            System.out.println("=== 판매자 회원가입 디버그 ===");
            System.out.println("U_USER 정보:");
            System.out.println("  U_ID: " + member.getUId());
            System.out.println("  U_PW (원본): " + member.getUPw());
            System.out.println("  U_NAME: " + member.getUName());
            System.out.println("  U_BIRTH: " + member.getUBirth());
            System.out.println("  U_GENDER: " + member.getUGender());
            System.out.println("  U_MAIL: " + member.getUMail());
            System.out.println("  U_PHONE: " + member.getUPhone());
            System.out.println("  U_POSTAL: " + member.getUPostal());
            System.out.println("  U_BASE_ADDR: " + member.getUBaseAddr());
            System.out.println("  U_DETAIL_ADDR: " + member.getUDetailAddr());
            System.out.println("  U_TYPE: " + member.getUType());
            System.out.println("  U_POINT: " + member.getUPoint());
            System.out.println("  U_RANK: " + member.getURank());
            System.out.println("  U_STATUS: " + member.getUStatus());
            
            System.out.println("SELLER_INFO 정보:");
            System.out.println("  S_U_ID: " + sellerInfo.getSUId());
            System.out.println("  S_COMPANY_NAME: " + sellerInfo.getSCompanyName());
            System.out.println("  S_SELLER_NO: " + sellerInfo.getSSellerNo());
            System.out.println("  S_SALES_REG_NUM: " + sellerInfo.getSSalesRegNum());
            System.out.println("  S_TEL: " + sellerInfo.getSTel());
            System.out.println("  S_FAX: " + sellerInfo.getSFax());
            System.out.println("  S_STATE: " + sellerInfo.getSState());
            System.out.println("  S_SELLER_TYPE: " + sellerInfo.getSSellerType());
            
            // 1. U_USER 테이블에 회원 정보 저장
            System.out.println("=== U_USER 테이블 저장 시작 ===");
            if (member.getUId().startsWith("user") || 
                member.getUId().startsWith("seller") || 
                member.getUId().startsWith("admin")) {
                System.out.println("평문 저장 선택됨");
            } else {
                System.out.println("BCrypt 암호화 선택됨");
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                String encodedPw = encoder.encode(member.getUPw());
                member.setUPw(encodedPw);
            }
            
            memberMapper.insertMember(member);
            System.out.println("U_USER 테이블 저장 완료");
            
            // 2. SELLER_INFO 테이블에 판매자 정보 저장
            System.out.println("=== SELLER_INFO 테이블 저장 시작 ===");
            memberMapper.insertSellerInfo(sellerInfo);
            System.out.println("SELLER_INFO 테이블 저장 완료");
            
            System.out.println("판매자 회원가입 완료: " + member.getUId());
            return member;
            
        } catch (Exception e) {
            System.out.println("판매자 회원가입 서비스 오류: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    // 임시 인증번호 저장용 (실제로는 Redis 사용 권장)
    private final java.util.concurrent.ConcurrentHashMap<String, String> emailVerificationCodes = new java.util.concurrent.ConcurrentHashMap<>();
    
}
