package umc.teamc.youthStepUp.calendar.service.command;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.teamc.youthStepUp.calendar.dto.request.UpdateBookmarkCompletionDTO;
import umc.teamc.youthStepUp.calendar.exception.BookmarkErrorCode;
import umc.teamc.youthStepUp.calendar.exception.BookmarkException;
import umc.teamc.youthStepUp.calendar.repository.BookmarkPolicyRepository;
import umc.teamc.youthStepUp.policy.entity.BookMarkPolicy;


@Service
@RequiredArgsConstructor
public class CalendarBookmarkCommandServiceImpl implements CalendarBookmarkCommandService {
    private final BookmarkPolicyRepository bookmarkPolicyRepository;

    @Transactional
    @Override
    public BookMarkPolicy updateIsCompleted(Long memberId, UpdateBookmarkCompletionDTO request) {
        BookMarkPolicy bookmarkPolicy = bookmarkPolicyRepository.findById(request.bookmarkId()).orElseThrow(() ->
                new BookmarkException(BookmarkErrorCode.NOT_FOUND));

        if (bookmarkPolicy.getMember().getId().equals(memberId)) {
            bookmarkPolicy.updateIsCompleted(request.isCompleted());
        } else throw new BookmarkException(BookmarkErrorCode.UNAUTHORIZED_ACCESS);
        return bookmarkPolicy;
    }
}
