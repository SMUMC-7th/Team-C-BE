package umc.teamc.youthStepUp.domain.profile.recode.response;

import java.util.List;

public record BookmarkSliceResponseRecord(List<BookmarkResponseRecord> bookmarkResponses,
                                          boolean hasNext,
                                          Long cursor) {
}
