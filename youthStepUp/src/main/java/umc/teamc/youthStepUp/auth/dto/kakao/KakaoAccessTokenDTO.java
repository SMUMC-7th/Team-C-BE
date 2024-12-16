package umc.teamc.youthStepUp.auth.dto.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KakaoAccessTokenDTO(
        @JsonProperty("token_type")
        String tokenType,
        @JsonProperty("access_token")
        String accessToken,
        @JsonProperty("id_token")
        String idToken,
        @JsonProperty("expires_in")
        Integer accessExpiresIn,
        @JsonProperty("refresh_token")
        String refreshToken,
        @JsonProperty("refresh_token_expires_in")
        Integer refreshExpiresIn
) {
}
