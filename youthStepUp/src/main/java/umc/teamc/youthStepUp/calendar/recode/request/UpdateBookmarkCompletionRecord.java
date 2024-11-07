package umc.teamc.youthStepUp.calendar.recode.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UpdateBookmarkCompletionRecord(
        @NotNull Long bookmarkId,
        boolean isComplete
) {
}
