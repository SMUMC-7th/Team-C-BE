package umc.teamc.youthStepUp.member.dto;

import umc.teamc.youthStepUp.member.entity.Member;

import java.util.List;

public record MemberProfileResponseDTO(
        String nickName,
        int age,
        Long kakaoId,
        List<String> regions,
        List<String> keywords,
        List<String> majors,
        String education
) {
    public static MemberProfileResponseDTO of(Member member, MemberInitProfileRequestDTO dto) {
        return new MemberProfileResponseDTO(
                member.getNickName(),
                member.getAge(),
                member.getKakaoId(),
                dto.regions(),
                dto.keyword(),
                dto.majors(),
                dto.educations());
    }
}