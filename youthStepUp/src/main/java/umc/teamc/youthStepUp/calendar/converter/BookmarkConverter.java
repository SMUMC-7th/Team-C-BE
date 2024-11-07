package umc.teamc.youthStepUp.calendar.converter;

import umc.teamc.youthStepUp.calendar.dto.response.BookmarkResponseByDateDTO;
import umc.teamc.youthStepUp.calendar.dto.response.BookmarkResponseByDateListDTO;
import umc.teamc.youthStepUp.calendar.dto.response.BookmarkResponseByMonthDTO;
import umc.teamc.youthStepUp.calendar.dto.response.BookmarkResponseByMonthListDTO;
import umc.teamc.youthStepUp.calendar.entity.Bookmark;

import java.util.List;

public class BookmarkConverter {

    public static BookmarkResponseByMonthDTO toBookmarkResponseByMonthRecord(Bookmark bookmark) {
        return BookmarkResponseByMonthDTO.builder()
                .id(bookmark.getId())
                .name(bookmark.getName())
                .policyPeriod(bookmark.getPolicyPeriod())
                .policyId(bookmark.getPolicy().getId())
                .build();

    }

    public static BookmarkResponseByDateDTO toBookmarkResponseByDateRecord(Bookmark bookmark) {
        return BookmarkResponseByDateDTO.builder()
                .id(bookmark.getId())
                .name(bookmark.getName())
                .policyPeriod(bookmark.getPolicyPeriod())
                .isCompleted(bookmark.isCompleted())
                .policyId(bookmark.getPolicy().getId())
                .build();
    }

    public static BookmarkResponseByMonthListDTO toBookmarkResponseByMonthListRecord(List<Bookmark> bookmarks) {
        List<BookmarkResponseByMonthDTO> records = bookmarks.stream()
                .map(BookmarkConverter::toBookmarkResponseByMonthRecord)
                .toList();
        return new BookmarkResponseByMonthListDTO(records);
    }

    public static BookmarkResponseByDateListDTO toBookmarkResponseByDateListRecord(List<Bookmark> bookmarks) {
        List<BookmarkResponseByDateDTO> records = bookmarks.stream()
                .map(BookmarkConverter::toBookmarkResponseByDateRecord)
                .toList();
        return new BookmarkResponseByDateListDTO(records);
    }


}
