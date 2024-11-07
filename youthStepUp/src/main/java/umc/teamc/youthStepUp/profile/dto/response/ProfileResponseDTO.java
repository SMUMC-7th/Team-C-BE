package umc.teamc.youthStepUp.profile.dto.response;

import lombok.Builder;
import umc.teamc.youthStepUp.member.entity.Education;

@Builder
public record ProfileResponseDTO(
        String nickName,
        int age,
        Education education
) {
}