package umc.teamc.youthStepUp.profile.exception;

import umc.teamc.youthStepUp.global.error.exception.CustomException;

public class BookmarkException extends CustomException {
    public BookmarkException(BookmarkErrorCode code) {
        super(code);
    }
}
