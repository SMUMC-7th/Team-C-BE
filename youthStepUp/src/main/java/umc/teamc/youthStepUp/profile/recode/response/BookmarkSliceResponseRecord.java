package umc.teamc.youthStepUp.profile.recode.response;

import java.util.List;

public record BookmarkSliceResponseRecord(List<BookmarkResponseRecord> bookmarkResponses,
                                          boolean hasNext,
                                          Long cursor) {
}
