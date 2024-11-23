package umc.teamc.youthStepUp.global.error.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import umc.teamc.youthStepUp.global.apiPayload.CustomResponse;
import umc.teamc.youthStepUp.global.error.BaseErrorCode;
import umc.teamc.youthStepUp.global.error.GeneralErrorCode;
import umc.teamc.youthStepUp.global.error.exception.CustomException;
import umc.teamc.youthStepUp.member.error.MemberErrorCode;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handle(CustomException e) {
        BaseErrorCode code = e.getCode();
        HttpStatus status = e.getCode().getStatus();
        return new ResponseEntity<>(CustomResponse.fail(code), status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handle(Exception e) {
        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR_500;
        HttpStatus status = code.getStatus();
        return new ResponseEntity<>(CustomResponse.fail(code), status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handle(MethodArgumentNotValidException e) {
        CustomResponse response = CustomResponse.fail(
                MemberErrorCode.PROFILE_VALIDATION_ERROR
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
