package umc.teamc.youthStepUp.profile.converter;

import java.util.ArrayList;
import org.springframework.data.domain.Slice;
import umc.teamc.youthStepUp.calendar.entity.Bookmark;
import umc.teamc.youthStepUp.profile.dto.response.BookmarkResponseRecord;
import umc.teamc.youthStepUp.profile.dto.response.BookmarkSliceResponseRecord;

public class BookmarkConverter {

    public static BookmarkResponseRecord toBookmarkResponseRecord(Bookmark bookmark) {
        return new BookmarkResponseRecord(
                bookmark.getId(),
                bookmark.getPolicy().getId(),
                bookmark.getName()
        );
    }

    public static BookmarkSliceResponseRecord toBookmarkSliceResponseRecord(Slice<Bookmark> bookmarks) {
        return new BookmarkSliceResponseRecord(
                bookmarks.getContent().isEmpty() ? new ArrayList<>()
                        : bookmarks.getContent().stream().map(BookmarkConverter::toBookmarkResponseRecord).toList(),
                bookmarks.hasNext(),
                bookmarks.getContent().isEmpty() ? 0
                        : bookmarks.getContent().get(bookmarks.getContent().size() - 1).getId()
        );
    }


}
