package umc.teamc.youthStepUp.profile.exception;

import umc.teamc.youthStepUp.global.error.exception.CustomException;

public class ProfileException extends CustomException {
    public ProfileException(ProfileErrorCode code) {
        super(code);
    }
}
