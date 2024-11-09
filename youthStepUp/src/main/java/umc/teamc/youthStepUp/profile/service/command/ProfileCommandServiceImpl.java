package umc.teamc.youthStepUp.profile.service.command;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.teamc.youthStepUp.member.entity.Education;
import umc.teamc.youthStepUp.member.entity.Keyword;
import umc.teamc.youthStepUp.member.entity.Major;
import umc.teamc.youthStepUp.member.entity.Member;
import umc.teamc.youthStepUp.member.entity.Region;
import umc.teamc.youthStepUp.member.error.MemberErrorCode;
import umc.teamc.youthStepUp.member.error.exception.MemberCustomException;
import umc.teamc.youthStepUp.profile.dto.request.UpdateProfileRequestDTO;
import umc.teamc.youthStepUp.profile.exception.ProfileErrorCode;
import umc.teamc.youthStepUp.profile.exception.ProfileException;
import umc.teamc.youthStepUp.profile.repository.ProfileRepository;

@Service
@RequiredArgsConstructor
public class ProfileCommandServiceImpl implements ProfileCommandService {

    private final ProfileRepository profileRepository;

    @Override
    @Transactional
    public Member updateProfile(Long memberId, UpdateProfileRequestDTO request) {
        Member profile = profileRepository.findById(memberId).orElseThrow(() ->
                new MemberCustomException(MemberErrorCode.MEMBER_NOT_FOUND));
        editProfile(request, profile);
        profileRepository.save(profile);
        return profile;
    }

    private static void editProfile(UpdateProfileRequestDTO request, Member profile) {
        if (request.age() != null) {
            profile.editAge(request.age());
        }
        if (request.nickName() != null) {
            profile.editNickName(request.nickName());
        }
        if (request.region() != null) {
            profile.editRegion(Region.toRegion(request.region()));
        }
        if (request.major() != null) {
            profile.editMajor(Major.toMajor(request.major()));
        }
        if (request.keywords() != null) {
            profile.editKeyword(Keyword.toKeyword((request.keywords())));
        }
        if (request.major() != null) {
            profile.editEducation(Education.toEducation(request.education()));
        }
    }

    @Override
    public void deleteProfile(Long memberId, String name) {
        Member profile = profileRepository.findById(memberId).orElseThrow(() ->
                new MemberCustomException(MemberErrorCode.MEMBER_NOT_FOUND));

        if (profile.getNickName().equals(name)) {
            //profile.setDeletedTime(LocalDateTime.now());
        } else {
            throw new ProfileException(ProfileErrorCode.BAD_REQUEST);
        }
    }

}
