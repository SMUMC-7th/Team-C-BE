package umc.teamc.youthStepUp.article.exception;

import umc.teamc.youthStepUp.global.error.BaseErrorCode;
import umc.teamc.youthStepUp.global.error.exception.CustomException;

public class ArticleErrorException extends CustomException {

    public ArticleErrorException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
