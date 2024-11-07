package umc.teamc.youthStepUp.profile.dto.request;


import umc.teamc.youthStepUp.member.entity.Education;
import umc.teamc.youthStepUp.member.entity.Keyword;
import umc.teamc.youthStepUp.member.entity.Major;
import umc.teamc.youthStepUp.member.entity.Region;

import java.util.List;

public record UpdateProfileRequestDTO(
        String nickName,
        Integer age,
        Education education,
        List<Major> major,
        List<Region> region,
        List<Keyword> keyword,
        String profileImg
) {
}
