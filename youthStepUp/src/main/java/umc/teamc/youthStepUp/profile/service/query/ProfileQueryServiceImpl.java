package umc.teamc.youthStepUp.profile.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.teamc.youthStepUp.member.entity.Member;
import umc.teamc.youthStepUp.profile.exception.ProfileErrorCode;
import umc.teamc.youthStepUp.profile.exception.ProfileException;
import umc.teamc.youthStepUp.profile.repository.ProfileRepository;


@Service
@RequiredArgsConstructor
public class ProfileQueryServiceImpl implements ProfileQueryService {

    private final ProfileRepository profileRepository;

    @Override
    public Member getProfile(Long profileId) {
        return profileRepository.findById(profileId).orElseThrow(() ->
                new ProfileException(ProfileErrorCode.NOT_FOUND));
    }
}

