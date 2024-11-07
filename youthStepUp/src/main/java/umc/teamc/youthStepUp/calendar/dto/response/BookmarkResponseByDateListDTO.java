package umc.teamc.youthStepUp.calendar.dto.response;


import java.util.List;

public record BookmarkResponseByDateListDTO(
        List<BookmarkResponseByDateDTO> bookmarks
) {
}