package umc.teamc.youthStepUp.policyInfo.exception;

import umc.teamc.youthStepUp.global.error.BaseErrorCode;
import umc.teamc.youthStepUp.global.error.exception.CustomException;

public class PolicyInfoException extends CustomException {
    public PolicyInfoException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
