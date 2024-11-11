package umc.teamc.youthStepUp.calendar.service.query;

import umc.teamc.youthStepUp.calendar.entity.Bookmark;

import java.util.List;

public interface CalendarBookmarkQueryService {
    public List<Bookmark> findByPolicyPeriodDate(String date);

    public List<Bookmark> findByPolicyPeriodMonth(String month);
}
