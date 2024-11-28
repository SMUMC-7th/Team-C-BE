package umc.teamc.youthStepUp.profile.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record BookmarkSliceResponseDTO(List<BookmarkResponseDTO> bookmarkResponses,
                                       boolean hasNext,
                                       Long cursor) {
}
