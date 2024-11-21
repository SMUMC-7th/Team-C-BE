package umc.teamc.youthStepUp.policy.exception;

import umc.teamc.youthStepUp.global.error.BaseErrorCode;
import umc.teamc.youthStepUp.global.error.exception.CustomException;

public class PolicyException extends CustomException {
    public PolicyException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
