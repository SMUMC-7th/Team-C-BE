package umc.teamc.youthStepUp.profile.recode.response;

import lombok.Builder;
import umc.teamc.youthStepUp.member.entity.Education;
import umc.teamc.youthStepUp.member.entity.Keyword;
import umc.teamc.youthStepUp.member.entity.Major;
import umc.teamc.youthStepUp.member.entity.Region;

import java.util.List;

@Builder
public record ProfileDetailResponseRecord(
        String nickName,
        Integer age,
        Education education,
        List<Major> major,
        List<Region> region,
        List<Keyword> keyword
) {
}