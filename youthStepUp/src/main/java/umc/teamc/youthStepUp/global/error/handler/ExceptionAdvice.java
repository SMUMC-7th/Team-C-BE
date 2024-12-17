package umc.teamc.youthStepUp.global.error.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import ch.qos.logback.core.status.ErrorStatus;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import umc.teamc.youthStepUp.global.apiPayload.CustomResponse;
import umc.teamc.youthStepUp.global.error.BaseErrorCode;
import umc.teamc.youthStepUp.global.error.GeneralErrorCode;
import umc.teamc.youthStepUp.global.error.exception.CustomException;
import umc.teamc.youthStepUp.validation.ValidateErrorCode;

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
        log.error("Exception: {}", e.getMessage());
        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR_500;
        HttpStatus status = code.getStatus();
        return new ResponseEntity<>(CustomResponse.fail(code), status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handle(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> validation(ConstraintViolationException e) {
        String errorMessage = e.getConstraintViolations().stream()
                .map(violation -> violation.getMessage())
                .findFirst()
                .orElse("요청 값이 유효하지 않습니다.");

        return ResponseEntity.badRequest().body(
                CustomResponse.fail(ValidateErrorCode.UNVALID_CALENDAR)
        );
    }

}
