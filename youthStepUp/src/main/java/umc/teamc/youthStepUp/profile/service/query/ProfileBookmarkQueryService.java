package umc.teamc.youthStepUp.profile.service.query;

import org.springframework.data.domain.Slice;
import umc.teamc.youthStepUp.profile.dto.response.BookmarkResponseDTO;

public interface ProfileBookmarkQueryService {
    public Slice<BookmarkResponseDTO> getBookmarks(Long cursor, int offset, Long memberId);
}
