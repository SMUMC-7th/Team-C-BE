package umc.teamc.youthStepUp.auth.dto;

public record TokenResponseDTO(
        String accessToken,
        long accessExpiresIn,
        String refreshToken,
        long refreshExpiresIn
) {

}
