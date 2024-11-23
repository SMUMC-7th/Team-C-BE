package umc.teamc.youthStepUp.profile.service.command;

import umc.teamc.youthStepUp.member.entity.Member;
import umc.teamc.youthStepUp.profile.dto.request.UpdateProfileRequestDTO;

public interface ProfileCommandService {
    Member updateProfile(Member member, UpdateProfileRequestDTO request);

    void deleteProfile(Member member, String name);

}
