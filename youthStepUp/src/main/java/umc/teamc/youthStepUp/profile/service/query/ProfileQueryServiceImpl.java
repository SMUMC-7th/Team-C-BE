package umc.teamc.youthStepUp.profile.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.teamc.youthStepUp.calendar.entity.Bookmark;
import umc.teamc.youthStepUp.calendar.repository.BookmarkRepository;
import umc.teamc.youthStepUp.member.entity.Member;
import umc.teamc.youthStepUp.profile.exception.ProfileErrorCode;
import umc.teamc.youthStepUp.profile.exception.ProfileException;
import umc.teamc.youthStepUp.profile.repository.ProfileRepository;


@Service
@RequiredArgsConstructor
public class ProfileQueryServiceImpl implements ProfileQueryService {

    private final ProfileRepository profileRepository;
    private final BookmarkRepository bookmarkRepository;

    @Transactional(readOnly = true)
    @Override
    public Member getProfile(Long profileId) {
        return profileRepository.findById(profileId).orElseThrow(() ->
                new ProfileException(ProfileErrorCode.NOT_FOUND));
    }

    @Transactional(readOnly = true)
    @Override
    public Slice<Bookmark> getBookmarks(Long cursor, int offset, Long memberId) {
        Pageable pageable = PageRequest.of(0, offset);
        if (cursor == null || cursor == 0) {
            return bookmarkRepository.findByMemberIdOrderByIdDesc(memberId, pageable);
        }
        return bookmarkRepository.findByMemberIdAndIdLessThanOrderByIdDesc(memberId, cursor, pageable);
    }
}

