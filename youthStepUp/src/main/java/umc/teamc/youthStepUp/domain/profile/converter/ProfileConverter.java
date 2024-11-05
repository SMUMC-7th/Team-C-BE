package umc.teamc.youthStepUp.domain.profile.converter;

import umc.teamc.youthStepUp.domain.profile.recode.request.UpdateProfileRequest;
import umc.teamc.youthStepUp.domain.profile.recode.response.ProfileDetailResponseRecord;
import umc.teamc.youthStepUp.domain.profile.recode.response.ProfileResponseRecord;
import umc.teamc.youthStepUp.member.entity.Member;

public class ProfileConverter {//이미지 추가해야함

    public static ProfileResponseRecord toProfileResponse(Member member) {
        return new ProfileResponseRecord(
                member.getNickName(),
                member.getAge(),
                member.getEducation()
        );
    }

    public static ProfileDetailResponseRecord toProfileDetailResponse(Member member) {

        return new ProfileDetailResponseRecord(
                member.getNickName(),
                member.getAge(),
                member.getEducation(),
                member.getMajor(),
                member.getRegion(),
                member.getKeyword()
        );
    }

    public static Member toMember(Long memberId, UpdateProfileRequest request) {
        return new Member(
                memberId, request.nickName(), request.age(), request.education(),
                request.region(), request.major(), request.keywords()
        );
    }
}
