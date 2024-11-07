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
    private BookmarkRepository bookmarkRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Bookmark> findByPolicyPeriodDate(String date) {
        return bookmarkRepository.findByDateWithinPolicyPeriod(date);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Bookmark> findByPolicyPeriodMonth(String month) {
        return bookmarkRepository.findByDateWithinPolicyPeriod(month);
    }
}
