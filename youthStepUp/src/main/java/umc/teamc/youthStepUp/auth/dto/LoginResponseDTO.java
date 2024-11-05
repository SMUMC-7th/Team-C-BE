package umc.teamc.youthStepUp.auth.dto;

public record LoginResponseDTO(
        String accessToken,
        long accessExpiresIn,
        String refreshToken,
        long refreshExpiresIn
) {

}
