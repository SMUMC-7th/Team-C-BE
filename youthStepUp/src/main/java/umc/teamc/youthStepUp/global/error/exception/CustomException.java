package umc.teamc.youthStepUp.global.error.exception;

import lombok.Getter;
import umc.teamc.youthStepUp.global.error.BaseErrorCode;

@Getter
public class CustomException extends RuntimeException{
    private final BaseErrorCode code;

    public CustomException(BaseErrorCode baseErrorCode) {
        this.code = baseErrorCode;
    }
}
