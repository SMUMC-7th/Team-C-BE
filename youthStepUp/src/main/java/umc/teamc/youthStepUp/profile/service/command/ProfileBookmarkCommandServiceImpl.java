package umc.teamc.youthStepUp.profile.service.command;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.teamc.youthStepUp.calendar.repository.BookmarkPolicyRepository;
import umc.teamc.youthStepUp.policy.entity.BookMarkPolicy;
import umc.teamc.youthStepUp.policy.entity.Policy;
import umc.teamc.youthStepUp.policy.repository.PolicyRepository;
import umc.teamc.youthStepUp.profile.exception.BookmarkErrorCode;
import umc.teamc.youthStepUp.profile.exception.BookmarkException;
import umc.teamc.youthStepUp.profile.exception.ProfileErrorCode;
import umc.teamc.youthStepUp.profile.exception.ProfileException;

@Service
@AllArgsConstructor
public class ProfileBookmarkCommandServiceImpl implements ProfileBookmarkCommandService {
    private final BookmarkPolicyRepository bookmarkPolicyRepository;
    private final PolicyRepository policyRepository;

    @Transactional
    @Override
    public void deleteBookmark(Long memberId, String srchPolicyId) {
        Policy policy = policyRepository.findBySrchPolicyId(srchPolicyId);
        if (policy == null) {
            throw new BookmarkException(BookmarkErrorCode.NOT_FOUND);
        }
        BookMarkPolicy bookmarkPolicy = bookmarkPolicyRepository.findById(policy.getId()).orElseThrow(() ->
                new BookmarkException(BookmarkErrorCode.NOT_FOUND));
        if (!(bookmarkPolicy.getMember().getId() == memberId)) {
            throw new ProfileException(ProfileErrorCode.FORBIDDEN);
        }
        bookmarkPolicyRepository.delete(bookmarkPolicy);
    }
}
