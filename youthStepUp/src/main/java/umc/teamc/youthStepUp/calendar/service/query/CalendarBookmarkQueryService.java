package umc.teamc.youthStepUp.calendar.service.query;

import umc.teamc.youthStepUp.calendar.entity.Bookmark;

import java.util.List;

public interface CalendarBookmarkQueryService {
    public List<Bookmark> findByPolicyPeriodDate(Long memberId, String date);

    public List<Bookmark> findByPolicyPeriodMonth(Long memberId, String month);
}
