package umc.teamc.youthStepUp.profile.dto.request;

import jakarta.validation.constraints.NotNull;

public record DeleteMemberRequestDTO(
        @NotNull(message = "탈퇴하기 위해 닉네임을 입력해야 합니다.")
        String nickName
) {
}
