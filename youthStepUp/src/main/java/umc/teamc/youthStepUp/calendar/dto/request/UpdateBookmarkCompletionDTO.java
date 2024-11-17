package umc.teamc.youthStepUp.calendar.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UpdateBookmarkCompletionDTO(
        @NotNull Long bookmarkId,
        @NotNull boolean isCompleted
) {
}
