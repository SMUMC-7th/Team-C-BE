package umc.teamc.youthStepUp.domain.calendar.service.command;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.teamc.youthStepUp.domain.calendar.entity.Bookmark;
import umc.teamc.youthStepUp.domain.calendar.exception.BookmarkErrorCode;
import umc.teamc.youthStepUp.domain.calendar.exception.BookmarkException;
import umc.teamc.youthStepUp.domain.calendar.repository.BookmarkRepository;

@Transactional
@Service
@RequiredArgsConstructor
public class CalendarBookmarkCommandServiceImpl implements CalendarBookmarkCommandService {
    private BookmarkRepository bookmarkRepository;

    @Override
    public Bookmark updateIsCompleted(Long bookmarkId, boolean isCompleted) {
        Bookmark bookmark = bookmarkRepository.findById(bookmarkId).orElseThrow(() ->
                new BookmarkException(BookmarkErrorCode.NOT_FOUND));
        bookmark.updateIsCompleted(isCompleted);
        return bookmark;
    }
}
