package umc.teamc.youthStepUp.profile.dto.request;

public record UpdateBookmarkCompletionDTO(
        Long bookmarkId,
        boolean isComplete
) {
}
