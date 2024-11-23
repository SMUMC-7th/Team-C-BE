package umc.teamc.youthStepUp.profile.service.command;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.teamc.youthStepUp.member.entity.Education;
import umc.teamc.youthStepUp.member.entity.Keyword;
import umc.teamc.youthStepUp.member.entity.Major;
import umc.teamc.youthStepUp.member.entity.Member;
import umc.teamc.youthStepUp.member.entity.Region;
import umc.teamc.youthStepUp.profile.dto.request.UpdateProfileRequestDTO;
import umc.teamc.youthStepUp.profile.exception.ProfileErrorCode;
import umc.teamc.youthStepUp.profile.exception.ProfileException;

@Service
@RequiredArgsConstructor
public class ProfileCommandServiceImpl implements ProfileCommandService {

    @Override
    @Transactional
    public Member updateProfile(Member member, UpdateProfileRequestDTO request) {
        editProfile(request, member);
        return member;
    }

    private static void editProfile(UpdateProfileRequestDTO request, Member profile) {
        if (request.age() != null) {
            profile.editAge(request.age());
        }
        if (!checkEmpty(request.nickName())) {
            profile.editNickName(request.nickName());
        }
        checkNullAndUpdateRegion(request.region(), profile);
        checkNullAndUpdateMajor(request.major(), profile);
        checkNullAndUpdateKeyWords(request.keywords(), profile);
        checkNullAndUpdateEducation(request.education(), profile);
    }

    private static void checkNullAndUpdateEducation(String educationDes, Member profile) {
        if (!checkEmpty(educationDes)) {
            Education education = Education.toEducation(educationDes);
            if (education != null) {
                profile.editEducation(education);
            }
        }
    }

    private static void checkNullAndUpdateKeyWords(List<String> keyword, Member profile) {
        if (keyword != null && !keyword.isEmpty()) {
            List<Keyword> keywords = Keyword.toKeyword(keyword);
            if (!keywords.contains(null)) {
                profile.editKeyword(keywords);
            }
        }
    }

    private static void checkNullAndUpdateMajor(List<String> major, Member profile) {
        if (major != null && !major.isEmpty()) {
            List<Major> majors = Major.toMajor(major);
            if (!majors.contains(null)) {
                profile.editMajor(majors);
            }
        }
    }

    private static void checkNullAndUpdateRegion(List<String> region, Member profile) {
        if (region != null && !region.isEmpty()) {
            List<Region> regions = Region.toRegion(region);
            if (!regions.contains(null)) {
                profile.editRegion(regions);
            }
        }
    }

    private static boolean checkEmpty(String str) {
        return str == null || str.isEmpty();
    }

    @Override
    @Transactional
    public void deleteProfile(Member profile, String name) {
        if (profile.getNickName().equals(name)) {
            profile.deleteEntity();
        } else {
            throw new ProfileException(ProfileErrorCode.BAD_REQUEST);
        }
    }

}
