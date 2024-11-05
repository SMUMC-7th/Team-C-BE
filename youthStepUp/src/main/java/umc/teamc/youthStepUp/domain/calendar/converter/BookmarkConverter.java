package umc.teamc.youthStepUp.domain.calendar.converter;

import umc.teamc.youthStepUp.domain.calendar.entity.Bookmark;
import umc.teamc.youthStepUp.domain.calendar.recode.response.BookmarkResponseByDateListRecord;
import umc.teamc.youthStepUp.domain.calendar.recode.response.BookmarkResponseByDateRecord;
import umc.teamc.youthStepUp.domain.calendar.recode.response.BookmarkResponseByMonthListRecord;
import umc.teamc.youthStepUp.domain.calendar.recode.response.BookmarkResponseByMonthRecord;

import java.util.List;
import java.util.stream.Collectors;

public class BookmarkConverter {

    public static BookmarkResponseByMonthRecord toResponseCalendarBookmarkByMonth(Bookmark bookmark) {
        return new BookmarkResponseByMonthRecord(
                bookmark.getId(),
                bookmark.getName(),
                bookmark.getPolicyPeriod(),
                bookmark.getPolicy().getId()
        );
    }

    public static BookmarkResponseByDateRecord toResponseCalendarBookmarkByDate(Bookmark bookmark) {
        return new BookmarkResponseByDateRecord(
                bookmark.getId(),
                bookmark.getName(),
                bookmark.getPolicyPeriod(),
                bookmark.isCompleted(),
                bookmark.getPolicy().getId()
        );
    }

    public static BookmarkResponseByMonthListRecord toResponseCalendarBookmarkByMonthList(List<Bookmark> bookmarks) {
        List<BookmarkResponseByMonthRecord> records = bookmarks.stream()
                .map(BookmarkConverter::toResponseCalendarBookmarkByMonth)
                .collect(Collectors.toList());
        return new BookmarkResponseByMonthListRecord(records);
    }

    public static BookmarkResponseByDateListRecord toResponseCalendarBookmarkByDateList(List<Bookmark> bookmarks) {
        List<BookmarkResponseByDateRecord> records = bookmarks.stream()
                .map(BookmarkConverter::toResponseCalendarBookmarkByDate)
                .collect(Collectors.toList());
        return new BookmarkResponseByDateListRecord(records);
    }
}
