package umc.teamc.youthStepUp.calendar.service.command;

import umc.teamc.youthStepUp.calendar.entity.Bookmark;


public interface CalendarBookmarkCommandService {
    public Bookmark updateIsCompleted(Long bookmarkId, boolean isCompleted);
}
