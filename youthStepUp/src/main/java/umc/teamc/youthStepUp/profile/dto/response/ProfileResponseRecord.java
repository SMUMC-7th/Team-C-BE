package umc.teamc.youthStepUp.profile.dto.response;

import umc.teamc.youthStepUp.member.entity.Education;

public record ProfileResponseRecord(
        String nickName,
        int age,
        Education education
) {
}