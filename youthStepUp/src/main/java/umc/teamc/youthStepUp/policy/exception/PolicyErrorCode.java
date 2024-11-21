package umc.teamc.youthStepUp.policy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.teamc.youthStepUp.global.error.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum PolicyErrorCode implements BaseErrorCode {
    POLICY_NOT_FOUND(HttpStatus.NOT_FOUND, "POLICY_NOT_FOUND", "정책을 찾을 수 없습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_NOT_FOUND", "사용자를 찾을 수 없습니다."),
    POLICY_BOOKMARK_ALREADY_EXISTS(HttpStatus.INTERNAL_SERVER_ERROR, "ALREADY_EXIST_BOOKMARK", "이미 존재하는 북마크입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
