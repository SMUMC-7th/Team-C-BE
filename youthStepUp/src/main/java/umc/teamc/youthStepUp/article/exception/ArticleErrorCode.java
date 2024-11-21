package umc.teamc.youthStepUp.article.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.teamc.youthStepUp.global.error.BaseErrorCode;

@AllArgsConstructor
@Getter
public enum ArticleErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE404", "게시글을 찾지 못했습니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "ARTICLE403", "접근 권한이 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}
