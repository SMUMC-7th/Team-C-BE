package umc.teamc.youthStepUp.auth.dto.naver;

public record NaverAccessTokenDTO(
        String access_token,
        String refresh_token,
        String token_type,
        String expires_in
) {
}
