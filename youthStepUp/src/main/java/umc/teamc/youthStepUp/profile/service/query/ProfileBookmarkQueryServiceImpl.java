package umc.teamc.youthStepUp.profile.service.query;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.teamc.youthStepUp.calendar.entity.Bookmark;
import umc.teamc.youthStepUp.calendar.repository.BookmarkRepository;


@Service
@AllArgsConstructor
public class ProfileBookmarkQueryServiceImpl implements ProfileBookmarkQueryService {

    private final BookmarkRepository bookmarkRepository;

    @Transactional(readOnly = true)
    @Override
    public Slice<Bookmark> getBookmarks(Long cursor, int offset, Long memberId) {
        Pageable pageable = PageRequest.of(0, offset);
        if (cursor == 0) {
            return bookmarkRepository.findByMemberIdOrderByIdDesc(memberId, pageable);
        }
        return bookmarkRepository.findByMemberIdAndIdLessThanOrderByIdDesc(memberId, cursor, pageable);
    }
}
