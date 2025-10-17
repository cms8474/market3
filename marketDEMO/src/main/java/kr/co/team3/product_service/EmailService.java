package kr.co.team3.product_service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.MailException;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    /**
     * 이메일 인증번호 발송
     * @param to 수신자 이메일
     * @param verificationCode 인증번호
     * @return 발송 결과 (성공: "SUCCESS", 실패: 오류 메시지)
     */
    public String sendVerificationEmail(String to, String verificationCode) {
        try {
            System.out.println("=== 이메일 발송 시작 ===");
            System.out.println("수신자: " + to);
            System.out.println("인증번호: " + verificationCode);

            // 이메일 형식 검증
            if (!isValidEmail(to)) {
                System.out.println("잘못된 이메일 형식: " + to);
                return "이메일 형식이 올바르지 않습니다.";
            }

            System.out.println("이메일 메시지 생성 시작...");
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("minstest22@gmail.com"); // 발신자 이메일
            message.setTo(to); // 수신자 이메일
            message.setSubject("[케이마켓] 이메일 인증번호"); // 이메일 제목
            message.setText("안녕하세요. 케이마켓입니다.\n\n" +
                    "회원가입을 위한 이메일 인증번호는 다음과 같습니다:\n\n" +
                    "인증번호: " + verificationCode + "\n\n" +
                    "위 인증번호를 회원가입 페이지에 입력해주세요.\n" +
                    "인증번호는 10분간 유효합니다.\n\n" +
                    "감사합니다."); // 이메일 내용

            System.out.println("이메일 메시지 생성 완료");
            System.out.println("발신자: " + message.getFrom());
            System.out.println("수신자: " + java.util.Arrays.toString(message.getTo()));
            System.out.println("제목: " + message.getSubject());
            System.out.println("내용 길이: " + (message.getText() != null ? message.getText().length() : 0) + "자");

            System.out.println("mailSender.send() 호출 시작...");
            mailSender.send(message);
            System.out.println("mailSender.send() 호출 완료");
            
            System.out.println("이메일 발송 성공: " + to);
            return "SUCCESS";
            
        } catch (MailException e) {
            System.out.println("이메일 발송 실패 (MailException): " + e.getMessage());
            System.out.println("예외 클래스: " + e.getClass().getName());
            System.out.println("오류 코드: " + getErrorCode(e));
            e.printStackTrace();
            
            // 구체적인 오류 메시지 반환
            String errorMessage = e.getMessage();
            if (errorMessage != null) {
                if (errorMessage.contains("Invalid Addresses") || 
                    errorMessage.contains("550") || 
                    errorMessage.contains("User unknown") ||
                    errorMessage.contains("mailbox unavailable")) {
                    return "이메일을 확인해주세요.";
                } else if (errorMessage.contains("Authentication failed") ||
                          errorMessage.contains("535")) {
                    return "이메일 서버 인증에 실패했습니다.";
                } else if (errorMessage.contains("Connection refused") ||
                          errorMessage.contains("timeout")) {
                    return "이메일 서버 연결에 실패했습니다.";
                }
            }
            return "이메일 발송에 실패했습니다: " + errorMessage;
            
        } catch (Exception e) {
            System.out.println("이메일 발송 실패 (Exception): " + e.getMessage());
            System.out.println("예외 클래스: " + e.getClass().getName());
            System.out.println("오류 코드: " + getErrorCode(e));
            e.printStackTrace();
            return "이메일 발송 중 오류가 발생했습니다: " + e.getMessage();
        }
    }

    /**
     * 이메일 형식 검증
     * @param email 검증할 이메일
     * @return 유효한 이메일 형식인지 여부
     */
    private boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    /**
     * 예외에서 오류 코드 추출
     * @param e 예외 객체
     * @return 오류 코드 문자열
     */
    private String getErrorCode(Exception e) {
        try {
            // MailException의 경우 메시지에서 오류 코드 추출 시도
            String message = e.getMessage();
            if (message != null) {
                // SMTP 오류 코드 패턴 찾기 (예: 550, 535, 421 등)
                java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("\\b(\\d{3})\\b");
                java.util.regex.Matcher matcher = pattern.matcher(message);
                if (matcher.find()) {
                    return "SMTP-" + matcher.group(1);
                }
                
                // 기타 오류 코드 패턴
                if (message.contains("Connection refused")) {
                    return "CONNECTION_REFUSED";
                } else if (message.contains("timeout")) {
                    return "TIMEOUT";
                } else if (message.contains("Authentication failed")) {
                    return "AUTH_FAILED";
                } else if (message.contains("Invalid Addresses")) {
                    return "INVALID_ADDRESS";
                }
            }
            
            // 예외 클래스명에서 오류 코드 생성
            return e.getClass().getSimpleName().toUpperCase();
            
        } catch (Exception ex) {
            return "UNKNOWN_ERROR";
        }
    }

    /**
     * 6자리 랜덤 인증번호 생성
     * @return 6자리 인증번호
     */
    public String generateVerificationCode() {
        return String.format("%06d", (int)(Math.random() * 1000000));
    }
}
