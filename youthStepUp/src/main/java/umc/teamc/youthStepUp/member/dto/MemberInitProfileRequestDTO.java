package umc.teamc.youthStepUp.member.dto;

import java.util.List;

public record MemberInitProfileRequestDTO(
        String age,
        List<String> regions,
        List<String> keyword,
        List<String> majors,
        String educations) {
}