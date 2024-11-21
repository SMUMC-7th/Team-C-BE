package umc.teamc.youthStepUp.policy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.teamc.youthStepUp.policy.entity.BookMarkPolicy;

public interface BookmarkRepository extends JpaRepository<BookMarkPolicy, Long> {
    BookMarkPolicy findBookMarkPolicyByMemberIdAndPolicyId(Long memberId, Long policyId);
}
