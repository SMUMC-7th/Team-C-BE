package umc.teamc.youthStepUp.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KakaoReissueAccessTokenDTO(
        @JsonProperty("id")
        Long id, //회원번호
        @JsonProperty("app_id")
        Integer appId,
        @JsonProperty("expires_in")
        Integer expiresIn
) {
}
