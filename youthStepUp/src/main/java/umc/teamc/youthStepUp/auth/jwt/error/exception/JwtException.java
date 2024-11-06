package umc.teamc.youthStepUp.auth.jwt.error.exception;

import lombok.Getter;
import umc.teamc.youthStepUp.global.error.BaseErrorCode;

@Getter
public class JwtException extends RuntimeException {
    private final BaseErrorCode code;

    public JwtException(BaseErrorCode baseErrorCode) {
        this.code = baseErrorCode;
    }
}
