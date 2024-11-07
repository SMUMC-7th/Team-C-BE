package umc.teamc.youthStepUp.auth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public record KakaoUserInfoDTO(
        @JsonInclude
        @JsonProperty("id")
        Long id,
        @JsonProperty("kakao_account")
        KakaoAccount kakaoAccount
) {

    public record KakaoAccount(
            @JsonProperty("profile") Profile profile
    ) {
    }

    public record Profile(
            @JsonProperty("nickname") String nickname,
            @JsonProperty("thumbnail_image_url") String thumbnailImageUrl,
            @JsonProperty("profile_image_url") String profileImageUrl,
            @JsonProperty("is_default_image") Boolean isDefaultImage,
            @JsonProperty("is_default_nickname") Boolean isDefaultNickname
    ) {
    }
}
