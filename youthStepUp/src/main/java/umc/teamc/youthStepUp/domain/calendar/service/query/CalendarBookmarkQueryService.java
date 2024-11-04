package umc.teamc.youthStepUp.domain.calendar.service.query;

import umc.teamc.youthStepUp.domain.calendar.entity.Bookmark;

import java.util.List;

public interface CalendarBookmarkQueryService {
    public List<Bookmark> findByPolicyPeriodDate(String date);

    public List<Bookmark> findByPolicyPeriodMonth(String month);
}
