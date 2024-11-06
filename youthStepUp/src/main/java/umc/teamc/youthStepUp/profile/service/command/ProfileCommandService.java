package umc.teamc.youthStepUp.profile.service.command;

import umc.teamc.youthStepUp.profile.recode.request.UpdateProfileRequest;
import umc.teamc.youthStepUp.member.entity.Member;

public interface ProfileCommandService {
    public Member updateProfile(Long memberId, UpdateProfileRequest request);

    public void deleteProfile(Long memberId, String name);

    public void deleteBookmark(Long bookmarkId);
}
