package umc.teamc.youthStepUp.calendar.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record BookmarkResponseByMonthDTO(
        @NotNull Long id,
        @NotNull String name,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDate startDate,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDate endDate
) {
}
