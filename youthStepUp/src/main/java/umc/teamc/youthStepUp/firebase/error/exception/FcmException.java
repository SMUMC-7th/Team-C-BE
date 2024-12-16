package umc.teamc.youthStepUp.firebase.error.exception;

import lombok.Getter;
import umc.teamc.youthStepUp.global.error.BaseErrorCode;
import umc.teamc.youthStepUp.global.error.exception.CustomException;

@Getter
public class FcmException extends CustomException {
    public FcmException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
