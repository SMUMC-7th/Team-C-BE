package umc.teamc.youthStepUp.profile.dto.response;

import java.util.List;

public record ProfileDetailResponseDTO(
        String nickName,
        String profileImg,
        Integer age,
        String education,
        List<String> major,
        List<String> region,
        List<String> keywords
) {
}