package umc.teamc.youthStepUp.member.dto;

import java.util.List;
import umc.teamc.youthStepUp.member.entity.Member;

public record MemberProfileResponseDTO(
        String nickName,
        int age,
        String socialId,
        List<String> regions,
        List<String> keywords,
        List<String> majors,
        String profileImg,
        String education
) {
    public static MemberProfileResponseDTO of(Member member, MemberInitProfileRequestDTO dto) {
        return new MemberProfileResponseDTO(
                member.getNickName(),
                member.getAge(),
                member.getSocialId(),
                dto.regions(),
                dto.keyword(),
                dto.majors(),
                dto.profileImg(),
                dto.educations());
    }
}