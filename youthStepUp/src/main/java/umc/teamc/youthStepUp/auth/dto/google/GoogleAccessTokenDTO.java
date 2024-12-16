package umc.teamc.youthStepUp.auth.dto.google;

public record GoogleAccessTokenDTO(
        String access_token,
        String returnSecureToken,
        String token_type,
        String expires_in

) {
}
