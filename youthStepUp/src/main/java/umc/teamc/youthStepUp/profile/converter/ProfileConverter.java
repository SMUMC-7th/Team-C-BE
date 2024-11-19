package umc.teamc.youthStepUp.profile.converter;

import umc.teamc.youthStepUp.member.entity.Keyword;
import umc.teamc.youthStepUp.member.entity.Major;
import umc.teamc.youthStepUp.member.entity.Member;
import umc.teamc.youthStepUp.member.entity.Region;
import umc.teamc.youthStepUp.profile.dto.response.ProfileDetailResponseDTO;
import umc.teamc.youthStepUp.profile.dto.response.ProfileResponseDTO;

public class ProfileConverter {

    public static ProfileResponseDTO toProfileResponse(Member member) {
        return new ProfileResponseDTO(
                member.getNickName(),
                member.getAge(),
                member.getEducation().getDescription(),
                member.getImgUrl()
        );
    }

    public static ProfileDetailResponseDTO toProfileDetailResponse(Member member) {

        return new ProfileDetailResponseDTO(
                member.getNickName(),
                member.getImgUrl(),
                member.getAge(),
                member.getEducation().getDescription(),
                member.getMajor()
                        .stream()
                        .map(Major::getDescription)
                        .toList(),
                member.getRegion()
                        .stream()
                        .map(Region::getDescription)
                        .toList(),
                member.getKeyword()
                        .stream()
                        .map(Keyword::getDescription)
                        .toList()
        );
    }
}
