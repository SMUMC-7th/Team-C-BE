package umc.teamc.youthStepUp.auth.dto.google;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GoogleUserInfoDTO(
        @JsonProperty("federatedId") String id,
        @JsonProperty("email") String email,
        @JsonProperty("emailVerified") boolean verifiedEmail,
        @JsonProperty("name") String name,
        @JsonProperty("picture") String picture
) {
}