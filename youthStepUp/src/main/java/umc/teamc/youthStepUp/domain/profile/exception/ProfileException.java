package umc.teamc.youthStepUp.domain.profile.exception;

import umc.teamc.youthStepUp.global.error.exception.CustomException;

public class ProfileException extends CustomException {
    public ProfileException(ProfileErrorCode code) {
        super(code);
    }
}
