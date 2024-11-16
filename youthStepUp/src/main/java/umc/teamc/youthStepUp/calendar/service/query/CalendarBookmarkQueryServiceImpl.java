package umc.teamc.youthStepUp.calendar.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.teamc.youthStepUp.calendar.entity.Bookmark;
import umc.teamc.youthStepUp.calendar.repository.BookmarkRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CalendarBookmarkQueryServiceImpl implements CalendarBookmarkQueryService {
    private final BookmarkRepository bookmarkRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Bookmark> findByPolicyPeriodDate(Long memberId, String date) {
        return bookmarkRepository.findByMemberIdAndDateWithinPolicyPeriod(memberId, date);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Bookmark> findByPolicyPeriodMonth(Long memberId, String month) {
        return bookmarkRepository.findByMemberIdAndMonthWithinPolicyPeriod(memberId, month);
    }
}
