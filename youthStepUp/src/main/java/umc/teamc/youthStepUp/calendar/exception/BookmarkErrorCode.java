package umc.teamc.youthStepUp.calendar.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.teamc.youthStepUp.global.error.BaseErrorCode;

@AllArgsConstructor
@Getter
public enum BookmarkErrorCode implements BaseErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND, "BOOKMARK_NOT_FOUND401", "북마크를 찾을 수 없습니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "BOOKMARK_WRONG_DATE_TYPE401", "잘못된 날짜 형식 입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
