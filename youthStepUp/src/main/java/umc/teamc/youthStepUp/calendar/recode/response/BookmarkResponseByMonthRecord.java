package umc.teamc.youthStepUp.calendar.recode.response;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record BookmarkResponseByMonthRecord(
        @NotNull Long id,
        @NotNull String name,
        @NotNull String policyPeriod,
        @NotNull Long policyId) {
}
