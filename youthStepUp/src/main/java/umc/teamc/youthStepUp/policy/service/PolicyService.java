package umc.teamc.youthStepUp.policy.service;

import umc.teamc.youthStepUp.policy.dto.PolicyBookmarkRequestDTO;
import umc.teamc.youthStepUp.policy.entity.BookMarkPolicy;
import umc.teamc.youthStepUp.policy.entity.Policy;

public interface PolicyService {

    Policy createPolicy(PolicyBookmarkRequestDTO.BookmarkRequestDTO requestDTO);

    BookMarkPolicy createBookmark(Long memberId, Long policyId);
}
