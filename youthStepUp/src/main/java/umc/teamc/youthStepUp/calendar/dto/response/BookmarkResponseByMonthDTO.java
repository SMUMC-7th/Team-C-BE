package umc.teamc.youthStepUp.calendar.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record BookmarkResponseByMonthDTO(
        @NotNull Long id,
        @NotNull String name,
        @NotNull String policyPeriod,
        @NotNull Long policyId) {
}
