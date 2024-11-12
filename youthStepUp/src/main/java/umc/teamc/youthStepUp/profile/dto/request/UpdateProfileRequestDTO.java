package umc.teamc.youthStepUp.profile.dto.request;

import java.util.List;

public record UpdateProfileRequestDTO(
        String nickName,
        Integer age,
        String education,
        List<String> major,
        List<String> region,
        List<String> keywords
) {
}
