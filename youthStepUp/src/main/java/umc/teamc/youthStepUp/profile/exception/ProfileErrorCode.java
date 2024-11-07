package umc.teamc.youthStepUp.profile.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.teamc.youthStepUp.global.error.BaseErrorCode;

@AllArgsConstructor
@Getter
public enum ProfileErrorCode implements BaseErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER_NOT_FOUND401", "해당 인원을 찾을 수 없습니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "MEMBER_NICNAME_WRONG400", "닉네임이 올바르지 않습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
