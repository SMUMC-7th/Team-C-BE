package umc.teamc.youthStepUp.member.dto;

import java.util.List;

public record MemberInitProfileRequestDTO(List<String> regions,
                                          List<String> keyword,
                                          List<String> majors,
                                          String educations) {
}