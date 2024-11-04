package umc.teamc.youthStepUp.domain.calendar.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.teamc.youthStepUp.domain.calendar.entity.Bookmark;
import umc.teamc.youthStepUp.domain.calendar.repository.BookmarkRepository;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CalendarBookmarkQueryServiceImpl implements CalendarBookmarkQueryService {
    private BookmarkRepository bookmarkRepository;

    @Override
    public List<Bookmark> findByPolicyPeriodDate(String date) {
        return bookmarkRepository.findByDateWithinPolicyPeriod(date);
    }

    @Override
    public List<Bookmark> findByPolicyPeriodMonth(String month) {
        return bookmarkRepository.findByDateWithinPolicyPeriod(month);
    }
}
