package umc.teamc.youthStepUp.global.success;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum GeneralSuccessCode implements BaseSuccessCode {
    OK(HttpStatus.OK, "200", "요청에 성공했습니다."),
    CREATED(HttpStatus.CREATED, "201", "자원이 생성되었습니다."),
    DELETED(HttpStatus.NO_CONTENT, "204", "성공적으로 삭제되었습니다.");
    // 필요한 필드값 선언
    private final HttpStatus status;
    private final String code;
    private final String message;

}
