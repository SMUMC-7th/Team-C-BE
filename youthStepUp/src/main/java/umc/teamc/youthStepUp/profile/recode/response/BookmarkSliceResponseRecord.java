package umc.teamc.youthStepUp.profile.recode.response;

import lombok.Builder;

import java.util.List;

@Builder
public record BookmarkSliceResponseRecord(
        List<BookmarkResponseRecord> bookmarkResponses,
        boolean hasNext,
        Long cursor
) {
}
