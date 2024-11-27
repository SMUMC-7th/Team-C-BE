package umc.teamc.youthStepUp.calendar.service.command;

import umc.teamc.youthStepUp.calendar.dto.request.UpdateBookmarkCompletionDTO;
import umc.teamc.youthStepUp.policy.entity.BookMarkPolicy;


public interface CalendarBookmarkCommandService {
    public BookMarkPolicy updateIsCompleted(Long memberId, UpdateBookmarkCompletionDTO request);
}
