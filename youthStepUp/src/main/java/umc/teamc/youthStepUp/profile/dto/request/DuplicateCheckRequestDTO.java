package umc.teamc.youthStepUp.profile.dto.request;

import jakarta.validation.constraints.NotNull;

public record DuplicateCheckRequestDTO(
        @NotNull
        String nickName
) {
}
