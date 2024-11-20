package umc.teamc.youthStepUp.profile.dto.response;

public record ProfileResponseDTO(
        String nickName,
        int age,
        String education,
        String profileImg
) {
}