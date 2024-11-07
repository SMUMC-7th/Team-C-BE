package umc.teamc.youthStepUp.profile.dto.response;

import lombok.Builder;

@Builder
public record BookmarkResponseDTO(
        Long bookmarkId,
        Long policyId,
        String name
) {
}
