package umc.teamc.youthStepUp.policyInfo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.teamc.youthStepUp.global.error.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum PolicyInfoErrorCode implements BaseErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND, "POLICYINFO_NOT_FOUND", "정책 용어 소개를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
