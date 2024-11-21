package umc.teamc.youthStepUp.policy.service;

import umc.teamc.youthStepUp.policy.entity.BookMarkPolicy;
import umc.teamc.youthStepUp.policy.entity.Policy;

public interface PolicyService {

    Policy createPolicy(Long memberId, String srchPolicyId, String rqutPrdCn);

    BookMarkPolicy createBookmark(Long memberId, Long policyId);
}
