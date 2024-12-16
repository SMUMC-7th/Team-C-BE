package umc.teamc.youthStepUp.auth.dto.naver;

import com.fasterxml.jackson.annotation.JsonProperty;

public record NaverUserInfoDTO(
        @JsonProperty("resultcode")
        String resultCode,
        @JsonProperty("message")
        String message,
        @JsonProperty("response")
        Response response

) {
    public record Response(
            @JsonProperty("id")
            String id,
            @JsonProperty("nickname")
            String nickname,
            @JsonProperty("name")
            String name,
            @JsonProperty("email")
            String email,
            @JsonProperty("gender")
            String gender,
            @JsonProperty("age")
            String age,
            @JsonProperty("birthday")
            String birthday,
            @JsonProperty("profile_image")
            String profile_image,
            @JsonProperty("birthyear")
            String birthyear,
            @JsonProperty("mobile")
            String mobile
    ) {

    }
}
