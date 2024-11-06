package umc.teamc.youthStepUp.member.error.exception;

import lombok.Getter;
import umc.teamc.youthStepUp.global.error.BaseErrorCode;
import umc.teamc.youthStepUp.global.error.exception.CustomException;

@Getter
public class MemberCustomException extends CustomException {
    public MemberCustomException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
