package umc.teamc.youthStepUp.profile.converter;

import org.springframework.data.domain.Slice;
import umc.teamc.youthStepUp.profile.dto.response.BookmarkResponseDTO;
import umc.teamc.youthStepUp.profile.dto.response.BookmarkSliceResponseDTO;

import java.util.ArrayList;

public class ProfileBookmarkConverter {

    public static BookmarkSliceResponseDTO toBookmarkSliceResponseDTO(Slice<BookmarkResponseDTO> bookmarks) {
        return BookmarkSliceResponseDTO.builder()
                .bookmarkResponses(bookmarks.getContent().isEmpty() ? new ArrayList<>() : bookmarks.getContent())
                .hasNext(bookmarks.hasNext())
                .cursor(bookmarks.getContent().isEmpty() ? 0 : bookmarks.getContent().get(bookmarks.getContent().size() - 1).bookmarkId())
                .build();
    }
}
