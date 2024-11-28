package umc.teamc.youthStepUp.profile.service.command;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.teamc.youthStepUp.calendar.repository.BookmarkPolicyRepository;
import umc.teamc.youthStepUp.policy.entity.BookMarkPolicy;
import umc.teamc.youthStepUp.profile.exception.BookmarkErrorCode;
import umc.teamc.youthStepUp.profile.exception.BookmarkException;
import umc.teamc.youthStepUp.profile.exception.ProfileErrorCode;
import umc.teamc.youthStepUp.profile.exception.ProfileException;

@Service
@AllArgsConstructor
public class ProfileBookmarkCommandServiceImpl implements ProfileBookmarkCommandService {
    private final BookmarkPolicyRepository bookmarkPolicyRepository;

    @Transactional
    //피그마에는 북마크 제거 버튼 없긴 함, 안 만들 계획 이라면 없애도 될 듯
    @Override
    public void deleteBookmark(Long memberId, Long bookmarkId) {
        BookMarkPolicy bookmarkPolicy = bookmarkPolicyRepository.findById(bookmarkId).orElseThrow(() ->
                new BookmarkException(BookmarkErrorCode.NOT_FOUND));
        if (!(bookmarkPolicy.getMember().getId() == memberId)) {
            throw new ProfileException(ProfileErrorCode.FORBIDDEN);
        }
        bookmarkPolicyRepository.delete(bookmarkPolicy);
    }
}
