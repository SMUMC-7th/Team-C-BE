package umc.teamc.youthStepUp.profile.recode.response;

import lombok.Builder;
import umc.teamc.youthStepUp.member.entity.Education;

@Builder
public record ProfileResponseRecord(
        String nickName,
        int age,
        Education education
) {
}