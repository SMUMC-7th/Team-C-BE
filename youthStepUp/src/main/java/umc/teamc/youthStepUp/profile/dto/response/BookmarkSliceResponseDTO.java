package umc.teamc.youthStepUp.profile.dto.response;

import java.util.List;

public record BookmarkSliceResponseDTO(List<BookmarkResponseDTO> bookmarkResponses,
                                       boolean hasNext,
                                       Long cursor) {
}
