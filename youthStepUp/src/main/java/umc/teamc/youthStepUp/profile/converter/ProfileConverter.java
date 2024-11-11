package umc.teamc.youthStepUp.profile.converter;

import umc.teamc.youthStepUp.member.entity.Member;
import umc.teamc.youthStepUp.profile.dto.response.ProfileDetailResponseDTO;
import umc.teamc.youthStepUp.profile.dto.response.ProfileResponseDTO;

public class ProfileConverter {//이미지 추가해야함

    public static ProfileResponseDTO toProfileResponse(Member member) {
        return ProfileResponseDTO.builder()
                .nickName(member.getNickName())
                .age(member.getAge())
                .education(member.getEducation())
                .build();
    }

    public static ProfileDetailResponseDTO toProfileDetailResponse(Member member) {
        return ProfileDetailResponseDTO.builder()
                .nickName(member.getNickName())
                .age(member.getAge())
                .education(member.getEducation())
                .major(member.getMajor())
                .region(member.getRegion())
                .keyword(member.getKeyword())
                .build();
    }
}
