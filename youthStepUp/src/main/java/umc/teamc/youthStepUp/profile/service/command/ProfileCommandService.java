package umc.teamc.youthStepUp.profile.service.command;

import umc.teamc.youthStepUp.member.entity.Member;
import umc.teamc.youthStepUp.profile.dto.request.UpdateProfileRequestDTO;

public interface ProfileCommandService {
    public Member updateProfile(Long memberId, UpdateProfileRequestDTO request);

    public void deleteProfile(Long memberId, String name);
    
}
