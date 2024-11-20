package umc.teamc.youthStepUp.calendar.service.command;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.teamc.youthStepUp.calendar.dto.request.UpdateBookmarkCompletionDTO;
import umc.teamc.youthStepUp.calendar.entity.Bookmark;
import umc.teamc.youthStepUp.calendar.exception.BookmarkErrorCode;
import umc.teamc.youthStepUp.calendar.exception.BookmarkException;
import umc.teamc.youthStepUp.calendar.repository.BookmarkRepository;


@Service
@RequiredArgsConstructor
public class CalendarBookmarkCommandServiceImpl implements CalendarBookmarkCommandService {
    private final BookmarkRepository bookmarkRepository;

    @Transactional
    @Override
    public Bookmark updateIsCompleted(Long memberId, UpdateBookmarkCompletionDTO request) {
        Bookmark bookmark = bookmarkRepository.findById(request.bookmarkId()).orElseThrow(() ->
                new BookmarkException(BookmarkErrorCode.NOT_FOUND));

        if (bookmark.getMember().getId().equals(memberId)) {
            bookmark.updateIsCompleted(request.isCompleted());
        } else throw new BookmarkException(BookmarkErrorCode.UNAUTHORIZED_ACCESS);
        return bookmark;
    }
}
