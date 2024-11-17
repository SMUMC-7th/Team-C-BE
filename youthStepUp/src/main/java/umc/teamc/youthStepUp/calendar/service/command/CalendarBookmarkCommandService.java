package umc.teamc.youthStepUp.calendar.service.command;

import umc.teamc.youthStepUp.calendar.dto.request.UpdateBookmarkCompletionDTO;
import umc.teamc.youthStepUp.calendar.entity.Bookmark;


public interface CalendarBookmarkCommandService {
    public Bookmark updateIsCompleted(Long memberId, UpdateBookmarkCompletionDTO request);
}
