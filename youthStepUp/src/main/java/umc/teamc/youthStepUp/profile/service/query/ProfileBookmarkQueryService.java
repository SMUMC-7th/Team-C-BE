package umc.teamc.youthStepUp.profile.service.query;

import org.springframework.data.domain.Slice;
import umc.teamc.youthStepUp.calendar.entity.Bookmark;

public interface ProfileBookmarkQueryService {
    public Slice<Bookmark> getBookmarks(Long cursor, int offset, Long memberId);
}
