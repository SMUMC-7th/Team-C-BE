package umc.teamc.youthStepUp.reply.error.exceptiion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.teamc.youthStepUp.global.error.BaseErrorCode;

@AllArgsConstructor
@Getter
public enum ReplyErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "Reply404", "댓글을 찾지 못했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
