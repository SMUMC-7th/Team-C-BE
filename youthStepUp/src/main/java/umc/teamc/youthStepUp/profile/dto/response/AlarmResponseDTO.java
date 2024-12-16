package umc.teamc.youthStepUp.profile.dto.response;

import java.util.List;

public record AlarmResponseDTO(
        List<AlarmDTO> alarms,
        boolean hasNext

) {
    public record AlarmDTO(
            String title,
            String body
    ) {
    }
}