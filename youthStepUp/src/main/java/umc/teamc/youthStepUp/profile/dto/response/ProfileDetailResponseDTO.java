package umc.teamc.youthStepUp.profile.dto.response;

import lombok.Builder;
import umc.teamc.youthStepUp.member.entity.Education;
import umc.teamc.youthStepUp.member.entity.Keyword;
import umc.teamc.youthStepUp.member.entity.Major;
import umc.teamc.youthStepUp.member.entity.Region;

import java.util.List;

@Builder
public record ProfileDetailResponseDTO(
        String nickName,
        Integer age,
        Education education,
        List<Major> major,
        List<Region> region,
        List<Keyword> keyword
) {
}