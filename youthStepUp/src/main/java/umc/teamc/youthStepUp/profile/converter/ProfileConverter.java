package umc.teamc.youthStepUp.profile.converter;

import umc.teamc.youthStepUp.member.entity.Member;
import umc.teamc.youthStepUp.profile.dto.response.ProfileDetailResponseRecord;
import umc.teamc.youthStepUp.profile.dto.response.ProfileResponseRecord;

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
}
