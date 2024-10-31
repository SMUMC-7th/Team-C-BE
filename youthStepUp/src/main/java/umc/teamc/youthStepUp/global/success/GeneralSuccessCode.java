package umc.teamc.youthStepUp.global.success;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum GeneralSuccessCode implements BaseSuccessCode {
    OK(HttpStatus.OK, "200", "Success"),
    CREATED(HttpStatus.CREATED, "201", "Resource Created");
    // 필요한 필드값 선언
    private final HttpStatus status;
    private final String code;
    private final String message;

}
