package umc.teamc.youthStepUp.reply.error.exceptiion;

import umc.teamc.youthStepUp.global.error.BaseErrorCode;
import umc.teamc.youthStepUp.global.error.exception.CustomException;

public class ReplyErrorException extends CustomException {

    public ReplyErrorException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
