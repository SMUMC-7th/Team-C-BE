package umc.teamc.youthStepUp.calendar.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record BookmarkResponseByMonthDTO(
        @NotNull Long id,
        @NotNull String name,
        LocalDate startDate,
        LocalDate endDate
) {
}
