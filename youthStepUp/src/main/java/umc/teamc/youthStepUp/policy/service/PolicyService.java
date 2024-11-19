package umc.teamc.youthStepUp.policy.service;

import umc.teamc.youthStepUp.policy.dto.PolicyBookmarkRequestDTO;
import umc.teamc.youthStepUp.policy.entity.BookMarkPolicy;
import umc.teamc.youthStepUp.policy.entity.Policy;

public interface PolicyService {

    Policy createPolicy(String srchPolicyId);
    BookMarkPolicy createBookmark(Long memberId, String srchPolicyId);
}
