package umc.teamc.youthStepUp.auth.jwt.error.exception;

import lombok.Getter;
import umc.teamc.youthStepUp.global.error.BaseErrorCode;
import umc.teamc.youthStepUp.global.error.exception.CustomException;

@Getter
public class JwtException extends CustomException {
    private final BaseErrorCode code;

    public JwtException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
        this.code = baseErrorCode;
    }
}
