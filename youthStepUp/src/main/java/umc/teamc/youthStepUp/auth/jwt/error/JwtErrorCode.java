package umc.teamc.youthStepUp.auth.jwt.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.teamc.youthStepUp.global.error.BaseErrorCode;

@AllArgsConstructor
@Getter
public enum JwtErrorCode implements BaseErrorCode {
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "TOKEN401", "토큰이 만료되었습니다."),
    TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "TOKEN401", "유효한 토큰 형식이 아닙니다."),
    HEADER_NO_TOKEN(HttpStatus.BAD_REQUEST, "TOKEN400", "헤더가 비어있습니다.");
    private final HttpStatus status;
    private final String code;
    private final String message;
}

