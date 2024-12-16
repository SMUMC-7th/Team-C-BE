package umc.teamc.youthStepUp.firebase.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.teamc.youthStepUp.global.error.BaseErrorCode;

@AllArgsConstructor
@Getter
public enum FcmErrorCode implements BaseErrorCode {
    INVALID_REQUEST_MESSAGE(HttpStatus.BAD_REQUEST, "FCM4001", "응답 값이 잘못되었습니다.");
    private final HttpStatus status;
    private final String code;
    private final String message;
}
