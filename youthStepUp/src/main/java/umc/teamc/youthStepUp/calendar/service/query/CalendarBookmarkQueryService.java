package umc.teamc.youthStepUp.calendar.service.query;

import umc.teamc.youthStepUp.calendar.dto.response.BookmarkResponseByDateDTO;
import umc.teamc.youthStepUp.calendar.dto.response.BookmarkResponseByMonthDTO;

import java.time.LocalDate;
import java.util.List;

public interface CalendarBookmarkQueryService {
    public List<BookmarkResponseByDateDTO> findByPolicyPeriodDate(Long memberId, LocalDate date);

    public List<BookmarkResponseByMonthDTO> findByPolicyPeriodMonth(Long memberId, LocalDate month);
}
