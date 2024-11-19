package umc.teamc.youthStepUp.policy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.teamc.youthStepUp.member.entity.Member;
import umc.teamc.youthStepUp.member.error.MemberErrorCode;
import umc.teamc.youthStepUp.member.error.exception.MemberCustomException;
import umc.teamc.youthStepUp.member.repository.MemberRepository;
import umc.teamc.youthStepUp.policy.dto.PolicyBookmarkRequestDTO;
import umc.teamc.youthStepUp.policy.entity.BookMarkPolicy;
import umc.teamc.youthStepUp.policy.entity.Policy;
import umc.teamc.youthStepUp.policy.exception.PolicyErrorCode;
import umc.teamc.youthStepUp.policy.exception.PolicyException;
import umc.teamc.youthStepUp.policy.repository.BookmarkRepository;
import umc.teamc.youthStepUp.policy.repository.PolicyRepository;

@Service
@RequiredArgsConstructor
public class PolicyServiceImpl implements PolicyService{

    private final PolicyRepository policyRepository;
    private final MemberRepository memberRepository;
    private final BookmarkRepository bookmarkRepository;

    @Override
    public Policy createPolicy(String srchPolicyId) {
        PolicyBookmarkRequestDTO.CreatePolicyDTO dto = new PolicyBookmarkRequestDTO.CreatePolicyDTO();
        Policy policy = dto.toEntity(srchPolicyId);
        return policyRepository.save(policy);
    }

    @Override
    public BookMarkPolicy createBookmark(Long memberId, String srchPolicyId) {

        //회원정보조회
        Member member = memberRepository.findById(memberId).orElseThrow(() ->
                new MemberCustomException(MemberErrorCode.MEMBER_NOT_FOUND));

        //정책정보조회
        Policy policy = policyRepository.findBySrchPolicyId(srchPolicyId);
        if (policy == null) {
            throw new PolicyException(PolicyErrorCode.POLICY_NOT_FOUND);
        }

        //북마크 객체 생성
        PolicyBookmarkRequestDTO.CreateBookmarkDTO dto = new PolicyBookmarkRequestDTO.CreateBookmarkDTO();
        BookMarkPolicy bookmark = dto.toEntity(member, policy);

        //DB에 저장
        return bookmarkRepository.save(bookmark);
    }
}
