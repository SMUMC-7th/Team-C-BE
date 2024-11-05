package umc.teamc.youthStepUp.domain.profile.recode.request;

import umc.teamc.youthStepUp.member.entity.Education;
import umc.teamc.youthStepUp.member.entity.Keyword;
import umc.teamc.youthStepUp.member.entity.Major;
import umc.teamc.youthStepUp.member.entity.Region;

import java.util.List;

public record UpdateProfileRequest(
        String nickName,
        Integer age,
        Education education,
        Major major,
        Region region,
        List<Keyword> keywords,
        String profileImg
) {
}
