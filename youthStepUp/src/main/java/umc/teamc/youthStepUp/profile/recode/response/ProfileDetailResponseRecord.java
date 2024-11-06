package umc.teamc.youthStepUp.profile.recode.response;

import umc.teamc.youthStepUp.member.entity.Education;
import umc.teamc.youthStepUp.member.entity.Keyword;
import umc.teamc.youthStepUp.member.entity.Major;
import umc.teamc.youthStepUp.member.entity.Region;

import java.util.List;

public record ProfileDetailResponseRecord(
        String nickName,
        Integer age,
        Education education,
        Major major,
        Region region,
        List<Keyword> keywords
) {
}