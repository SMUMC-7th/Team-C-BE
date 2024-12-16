package umc.teamc.youthStepUp.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.teamc.youthStepUp.global.error.BaseErrorCode;

@AllArgsConstructor
@Getter
public enum ValidateErrorCode implements BaseErrorCode {
    UNVALID_YEAR(HttpStatus.FORBIDDEN, "CALENDAR400", "형식에 맞지 않는 YEAR입니다."),
    UNVALID_MONTH(HttpStatus.FORBIDDEN, "CALENDAR400", "형식에 맞지 않는 MONTH입니다."),
    UNVALID_DAY(HttpStatus.FORBIDDEN, "CALENDAR400", "형식에 맞지 않는 DAY입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}
