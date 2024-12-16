package umc.teamc.youthStepUp.auth.success;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.teamc.youthStepUp.global.success.BaseSuccessCode;

@AllArgsConstructor
@Getter
public enum AuthSuccessCode implements BaseSuccessCode {
    //200
    LOGIN_SUCCESS(HttpStatus.OK, "AUTH200", "로그인 되었습니다."),
    DEVICE_SUCCESS(HttpStatus.OK, "AUTH200", "디바이스 토큰이 지정 되었습니다."),
    LOGOUT_SUCCESS(HttpStatus.OK, "AUTH200", "로그아웃 되었습니다."),
    //201
    SIGN_UP_SUCCESS(HttpStatus.CREATED, "AUTH201", "회원가입 되었습니다."),
    //202
    AUTH_CODE_SUCCESS(HttpStatus.ACCEPTED, "AUTH202", "인가 코드 발급이 성공했습니다."),
    ACCESS_TOKEN_REISSUE_SUCCESS(HttpStatus.ACCEPTED, "AUTH202", "엑세스 토큰이 재발급 되었습니다.");
    // 필요한 필드값 선언
    private final HttpStatus status;
    private final String code;
    private final String message;

}
