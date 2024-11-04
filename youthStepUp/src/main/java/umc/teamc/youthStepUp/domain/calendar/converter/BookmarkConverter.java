package umc.teamc.youthStepUp.domain.calendar.converter;

import umc.teamc.youthStepUp.domain.calendar.entity.Bookmark;
import umc.teamc.youthStepUp.domain.calendar.recode.response.ResponseCalendarBookmarkByDateListRecord;
import umc.teamc.youthStepUp.domain.calendar.recode.response.ResponseCalendarBookmarkByDateRecord;
import umc.teamc.youthStepUp.domain.calendar.recode.response.ResponseCalendarBookmarkByMonthListRecord;
import umc.teamc.youthStepUp.domain.calendar.recode.response.ResponseCalendarBookmarkByMonthRecord;

import java.util.List;
import java.util.stream.Collectors;

public class BookmarkConverter {

    public static ResponseCalendarBookmarkByMonthRecord toResponseCalendarBookmarkByMonth(Bookmark bookmark) {
        return new ResponseCalendarBookmarkByMonthRecord(
                bookmark.getId(),
                bookmark.getPolicyPeriod(),
                bookmark.getPolicy().getId()
        );
    }

    public static ResponseCalendarBookmarkByDateRecord toResponseCalendarBookmarkByDate(Bookmark bookmark) {
        return new ResponseCalendarBookmarkByDateRecord(
                bookmark.getId(),
                bookmark.isCompleted(),
                bookmark.getPolicyPeriod(),
                bookmark.getPolicy().getId()
        );
    }

    public static ResponseCalendarBookmarkByMonthListRecord toResponseCalendarBookmarkByMonthList(List<Bookmark> bookmarks) {
        List<ResponseCalendarBookmarkByMonthRecord> records = bookmarks.stream()
                .map(BookmarkConverter::toResponseCalendarBookmarkByMonth)
                .collect(Collectors.toList());
        return new ResponseCalendarBookmarkByMonthListRecord(records);
    }

    public static ResponseCalendarBookmarkByDateListRecord toResponseCalendarBookmarkByDateList(List<Bookmark> bookmarks) {
        List<ResponseCalendarBookmarkByDateRecord> records = bookmarks.stream()
                .map(BookmarkConverter::toResponseCalendarBookmarkByDate)
                .collect(Collectors.toList());
        return new ResponseCalendarBookmarkByDateListRecord(records);
    }
}
