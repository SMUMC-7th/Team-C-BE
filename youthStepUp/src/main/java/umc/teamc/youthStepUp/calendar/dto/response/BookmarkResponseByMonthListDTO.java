package umc.teamc.youthStepUp.calendar.dto.response;


import java.util.List;

public record BookmarkResponseByMonthListDTO(
        List<BookmarkResponseByMonthDTO> bookmarks
) {
}