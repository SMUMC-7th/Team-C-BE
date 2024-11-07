package umc.teamc.youthStepUp.profile.converter;

import umc.teamc.youthStepUp.member.entity.Member;
import umc.teamc.youthStepUp.profile.recode.request.UpdateProfileRequest;
import umc.teamc.youthStepUp.profile.recode.response.ProfileDetailResponseRecord;
import umc.teamc.youthStepUp.profile.recode.response.ProfileResponseRecord;

public class ProfileConverter {//이미지 추가해야함

    public static ProfileResponseRecord toProfileResponse(Member member) {
        return ProfileResponseRecord.builder()
                .nickName(member.getNickName())
                .age(member.getAge())
                .education(member.getEducation())
                .build();
    }

    public static ProfileDetailResponseRecord toProfileDetailResponse(Member member) {
        return ProfileDetailResponseRecord.builder()
                .nickName(member.getNickName())
                .age(member.getAge())
                .education(member.getEducation())
                .major(member.getMajor())
                .region(member.getRegion())
                .keyword(member.getKeyword())
                .build();
    }

    public static Member toMember(Long memberId, UpdateProfileRequest request) {
        return Member.builder()
                .id(memberId)
                .nickName(request.nickName())
                .age(request.age())
                .education(request.education())
                .region(request.region())
                .keyword(request.keyword())
                .major(request.major())
                .build();
    }
}
