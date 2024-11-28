package umc.teamc.youthStepUp.calendar.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.teamc.youthStepUp.calendar.dto.response.BookmarkResponseByDateDTO;
import umc.teamc.youthStepUp.calendar.dto.response.BookmarkResponseByMonthDTO;
import umc.teamc.youthStepUp.calendar.repository.BookmarkPolicyRepository;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CalendarBookmarkQueryServiceImpl implements CalendarBookmarkQueryService {
    private final BookmarkPolicyRepository bookmarkPolicyRepository;

    @Transactional(readOnly = true)
    @Override
    public List<BookmarkResponseByDateDTO> findByPolicyPeriodDate(Long memberId, LocalDate date) {
        return bookmarkPolicyRepository.findByMemberIdAndDateWithinPolicyPeriod(memberId, date);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookmarkResponseByMonthDTO> findByPolicyPeriodMonth(Long memberId, LocalDate month) {
        return bookmarkPolicyRepository.findByMemberIdAndMonthWithinPolicyPeriod(memberId, month);
    }
}
