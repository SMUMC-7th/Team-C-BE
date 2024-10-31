package umc.teamc.youthStepUp.global.error;

import org.springframework.http.HttpStatus;
/*
- 에러를 담는 코드. Exception이 터질 때 에러에 대한 상세 내용과 터트림.
 */

public interface BaseErrorCode {
    HttpStatus getStatus();

    String getCode();

    String getMessage();
}
