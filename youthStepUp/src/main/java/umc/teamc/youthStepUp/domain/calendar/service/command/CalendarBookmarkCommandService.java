package umc.teamc.youthStepUp.domain.calendar.service.command;

import umc.teamc.youthStepUp.domain.calendar.entity.Bookmark;


public interface CalendarBookmarkCommandService {
    public Bookmark updateIsCompleted(Long bookmarkId, boolean isCompleted);
}
