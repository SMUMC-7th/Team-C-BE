package umc.teamc.youthStepUp.member.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.teamc.youthStepUp.global.error.BaseErrorCode;

@AllArgsConstructor
@Getter
public enum MemberErrorCode implements BaseErrorCode {
    //회원가입
    NICK_IS_DUPLICATE(HttpStatus.CONFLICT, "MEMBER409", "이미 존재하는 닉네임입니다."),
    NICK_NAME_NOT_EXIST(HttpStatus.NOT_FOUND, "MEMBER40401", "닉네임이 존재하지 않습니다."),
    //
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER40402", "해당 회원이 존재하지 않습니다.");
    private final HttpStatus status;
    private final String code;
    private final String message;
}

