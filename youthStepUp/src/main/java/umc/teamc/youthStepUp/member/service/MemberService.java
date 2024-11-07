package umc.teamc.youthStepUp.member.service;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.teamc.youthStepUp.member.dto.MemberInitProfileRequestDTO;
import umc.teamc.youthStepUp.member.dto.MemberProfileResponseDTO;
import umc.teamc.youthStepUp.member.entity.Education;
import umc.teamc.youthStepUp.member.entity.Keyword;
import umc.teamc.youthStepUp.member.entity.Major;
import umc.teamc.youthStepUp.member.entity.Member;
import umc.teamc.youthStepUp.member.entity.Region;
import umc.teamc.youthStepUp.member.error.MemberErrorCode;
import umc.teamc.youthStepUp.member.error.exception.MemberCustomException;
import umc.teamc.youthStepUp.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public MemberProfileResponseDTO initProfile(MemberInitProfileRequestDTO dto) {
        Member member = memberRepository.findById(1L).orElseThrow(
                () -> new MemberCustomException(MemberErrorCode.MEMBER_NOT_FOUND)
        ); //todo: Context에서 사용자 정보 꺼내는 로직 생성
        Education education = Education.toEducation(dto.educations());
        List<Region> regions = Region.toRegion(dto.regions());
        List<Major> majors = Major.toMajor(dto.majors());
        List<Keyword> keywords = Keyword.toKeyword(dto.keyword());
        validateInitprofileIsNull(education, regions, majors, keywords);
        initProfile(member, education, regions, majors, keywords);
        memberRepository.save(member);
        return MemberProfileResponseDTO.of(member, dto);
    }

    private static void initProfile(Member member, Education education, List<Region> regions, List<Major> majors,
                                    List<Keyword> keywords) {
        member.editEducation(education);
        member.editRegion(regions);
        member.editKeyword(keywords);
        member.editMajor(majors);
    }

    private static void validateInitprofileIsNull(Education education, List<Region> regions, List<Major> majors,
                                                  List<Keyword> keywords) {
        if (education == null || regions.contains(null) || majors.contains(null) || keywords.contains(null)) {
            throw new MemberCustomException(MemberErrorCode.INPUT_ENTER_REQURIED_INFORMATION);
        }
    }

}
