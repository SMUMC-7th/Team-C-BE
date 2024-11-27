package umc.teamc.youthStepUp.calendar.dto.response;

import lombok.Builder;

@Builder
public record UpdatedBookmarkIsCompletedDTO(Long bookmarkId, boolean isCompleted) {
}
