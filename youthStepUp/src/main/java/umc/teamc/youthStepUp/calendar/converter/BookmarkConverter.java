package umc.teamc.youthStepUp.calendar.converter;

import umc.teamc.youthStepUp.calendar.entity.Bookmark;
import umc.teamc.youthStepUp.calendar.recode.response.BookmarkResponseByDateListRecord;
import umc.teamc.youthStepUp.calendar.recode.response.BookmarkResponseByDateRecord;
import umc.teamc.youthStepUp.calendar.recode.response.BookmarkResponseByMonthListRecord;
import umc.teamc.youthStepUp.calendar.recode.response.BookmarkResponseByMonthRecord;

import java.util.List;

public class BookmarkConverter {

    public static BookmarkResponseByMonthRecord toBookmarkResponseByMonthRecord(Bookmark bookmark) {
        return new BookmarkResponseByMonthRecord(
                bookmark.getId(),
                bookmark.getName(),
                bookmark.getPolicyPeriod(),
                bookmark.getPolicy().getId()
        );
    }

    public static BookmarkResponseByDateRecord toBookmarkResponseByDateRecord(Bookmark bookmark) {
        return new BookmarkResponseByDateRecord(
                bookmark.getId(),
                bookmark.getName(),
                bookmark.getPolicyPeriod(),
                bookmark.isCompleted(),
                bookmark.getPolicy().getId()
        );
    }

    public static BookmarkResponseByMonthListRecord toBookmarkResponseByMonthListRecord(List<Bookmark> bookmarks) {
        List<BookmarkResponseByMonthRecord> records = bookmarks.stream()
                .map(BookmarkConverter::toBookmarkResponseByMonthRecord)
                .toList();
        return new BookmarkResponseByMonthListRecord(records);
    }

    public static BookmarkResponseByDateListRecord toBookmarkResponseByDateListRecord(List<Bookmark> bookmarks) {
        List<BookmarkResponseByDateRecord> records = bookmarks.stream()
                .map(BookmarkConverter::toBookmarkResponseByDateRecord)
                .toList();
        return new BookmarkResponseByDateListRecord(records);
    }


}
