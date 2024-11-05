package umc.teamc.youthStepUp.domain.profile.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.teamc.youthStepUp.domain.profile.entity.Bookmark;
import umc.teamc.youthStepUp.domain.profile.exception.ProfileErrorCode;
import umc.teamc.youthStepUp.domain.profile.exception.ProfileException;
import umc.teamc.youthStepUp.domain.profile.repository.BookmarkRepository;
import umc.teamc.youthStepUp.domain.profile.repository.ProfileRepository;
import umc.teamc.youthStepUp.member.entity.Member;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProfileQueryServiceImpl implements ProfileQueryService {

    private final ProfileRepository profileRepository;
    private final BookmarkRepository bookmarkRepository;

    @Override
    public Member getProfile(Long profileId) {
        return profileRepository.findById(profileId).orElseThrow(() ->
                new ProfileException(ProfileErrorCode.NOT_FOUND));
    }

    @Override
    public Slice<Bookmark> getBookmarks(Long cursor, int offset, Long memberId) {
        Pageable pageable = PageRequest.of(0, offset);
        if (cursor == 0) {
            return bookmarkRepository.findByMemberIdOrderByIdDesc(memberId, pageable);
        }
        return bookmarkRepository.findByMemberIdAndIdLessThanOrderByIdDesc(memberId, cursor, pageable);
    }
}

