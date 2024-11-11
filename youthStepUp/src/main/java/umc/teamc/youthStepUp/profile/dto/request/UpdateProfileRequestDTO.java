package umc.teamc.youthStepUp.profile.dto.request;

import lombok.Builder;

import java.util.List;

@Builder
public record UpdateProfileRequestDTO(
        String nickName,
        Integer age,
        String education,
        List<String> major,
        List<String> region,
        List<String> keyword
) {
}
