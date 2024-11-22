package umc.teamc.youthStepUp.member.dto;

import java.util.List;

public record MemberKeyWordDTO(
        List<String> keywords,
        List<String> regions

) {
}