package umc.teamc.youthStepUp.member.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record MemberInitProfileRequestDTO(
        String nickname,
        @NotNull(message = "나이를 입력해주세요")
        int age,
        @NotNull(message = "지역을 입력해주세요")
        List<String> regions,
        @NotNull(message = "관심 분야를 입력해주세요")
        List<String> keyword,
        @NotNull(message = "전공을 입력해주세요")
        List<String> majors,
        @NotNull(message = "프로필 이미지를 입력해주세요")
        String profileImg,
        @NotNull(message = "학력을 입력해주세요")
        String educations) {
}