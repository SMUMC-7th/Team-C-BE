package umc.teamc.youthStepUp.calendar.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Builder;

@Builder
public record BookmarkResponseByDateDTO(
        @NotNull Long id,
        @NotNull String name,
        @JsonFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
        @JsonFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
        @NotNull boolean isCompleted,
        @NotNull String srchPolicyId
) {
}
