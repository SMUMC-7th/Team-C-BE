package umc.teamc.youthStepUp.calendar.converter;

import umc.teamc.youthStepUp.calendar.dto.response.*;
import umc.teamc.youthStepUp.policy.entity.BookMarkPolicy;

import java.util.List;

public class BookmarkConverter {

    public static BookmarkResponseByMonthListDTO toBookmarkResponseByMonthListRecord(List<BookmarkResponseByMonthDTO> bookmarkPolicies) {
        return new BookmarkResponseByMonthListDTO(bookmarkPolicies);
    }

    public static BookmarkResponseByDateListDTO toBookmarkResponseByDateListRecord(List<BookmarkResponseByDateDTO> bookmarkPolicies) {
        return new BookmarkResponseByDateListDTO(bookmarkPolicies);
    }

    public static UpdatedBookmarkIsCompletedDTO toUpdatedBookmarkIsCompletedRecord(BookMarkPolicy bookMarkPolicy) {
        return UpdatedBookmarkIsCompletedDTO.builder()
                .bookmarkId(bookMarkPolicy.getId())
                .isCompleted(bookMarkPolicy.getIsCompleted())
                .build();
    }
}
