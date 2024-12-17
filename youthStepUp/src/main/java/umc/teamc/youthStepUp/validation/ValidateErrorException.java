package umc.teamc.youthStepUp.validation;

import umc.teamc.youthStepUp.global.error.BaseErrorCode;
import umc.teamc.youthStepUp.global.error.exception.CustomException;

public class ValidateErrorException extends CustomException {
    public ValidateErrorException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
