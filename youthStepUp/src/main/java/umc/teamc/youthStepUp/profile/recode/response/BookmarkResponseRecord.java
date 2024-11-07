package umc.teamc.youthStepUp.profile.recode.response;

import lombok.Builder;

@Builder
public record BookmarkResponseRecord(
        Long bookmarkId,
        Long policyId,
        String name
) {
}
