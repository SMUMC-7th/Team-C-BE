package umc.teamc.youthStepUp.profile.service.query;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.teamc.youthStepUp.calendar.repository.BookmarkPolicyRepository;
import umc.teamc.youthStepUp.profile.dto.response.BookmarkResponseDTO;


@Service
@AllArgsConstructor
public class ProfileBookmarkQueryServiceImpl implements ProfileBookmarkQueryService {

    private final BookmarkPolicyRepository bookmarkPolicyRepository;

    @Transactional(readOnly = true)
    @Override
    public Slice<BookmarkResponseDTO> getBookmarks(Long cursor, int offset, Long memberId) {
        Pageable pageable = PageRequest.of(0, offset);
        if (cursor == 0) {
            return bookmarkPolicyRepository.findByMemberIdAndDeletedAtIsNullOrderByIdDesc(memberId, pageable);
        }
        return bookmarkPolicyRepository.findByMemberIdAndDeletedAtIsNullAndIdLessThanOrderByIdDesc(memberId, cursor, pageable);
    }
}
