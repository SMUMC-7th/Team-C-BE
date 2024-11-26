package umc.teamc.youthStepUp.auth.error.exception;

import lombok.Getter;
import umc.teamc.youthStepUp.global.error.BaseErrorCode;
import umc.teamc.youthStepUp.global.error.exception.CustomException;

@Getter
public class KakaoAuthException extends CustomException {
    private final BaseErrorCode code;

    public KakaoAuthException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
        this.code = baseErrorCode;
    }
}
