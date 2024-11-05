package umc.teamc.youthStepUp.domain.profile.recode.response;

import umc.teamc.youthStepUp.member.entity.Education;

public record ProfileResponseRecord(
        String nickName,
        int age,
        Education education
) {
}