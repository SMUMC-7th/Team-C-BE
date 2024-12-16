package umc.teamc.youthStepUp.member.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.teamc.youthStepUp.global.error.BaseErrorCode;

@AllArgsConstructor
@Getter
public enum MemberErrorCode implements BaseErrorCode {
    PROFILE_VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "MEMBER4001", "필수 정보를 입력해주세요."),
    INPUT_ENTER_REQURIED_INFORMATION(HttpStatus.BAD_REQUEST, "MEMBER4002", "일치하는 값이 없습니다."),
    NICK_IS_DUPLICATE(HttpStatus.CONFLICT, "MEMBER40901", "이미 존재하는 닉네임입니다."),
    NICK_NAME_NOT_EXIST(HttpStatus.NOT_FOUND, "MEMBER40401", "닉네임이 존재하지 않습니다."),
    MEMBER_DEVICE_TOKEN(HttpStatus.NOT_FOUND, "MEMBER40402", "디바이스 토큰이 존재하지 않습니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER40402", "해당 회원이 존재하지 않습니다.");
    private final HttpStatus status;
    private final String code;
    private final String message;
}
