package umc.teamc.youthStepUp.calendar.recode.response;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record BookmarkResponseByDateRecord(
        @NotNull Long id,
        @NotNull String name,
        @NotNull String policyPeriod,
        @NotNull boolean isCompleted,
        Long policyId
) {
}
