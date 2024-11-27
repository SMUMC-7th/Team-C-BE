package umc.teamc.youthStepUp.policy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.teamc.youthStepUp.calendar.repository.BookmarkPolicyRepository;
import umc.teamc.youthStepUp.member.entity.Member;
import umc.teamc.youthStepUp.member.error.MemberErrorCode;
import umc.teamc.youthStepUp.member.error.exception.MemberCustomException;
import umc.teamc.youthStepUp.member.repository.MemberRepository;
import umc.teamc.youthStepUp.policy.dto.PolicyBookmarkRequestDTO;
import umc.teamc.youthStepUp.policy.entity.BookMarkPolicy;
import umc.teamc.youthStepUp.policy.entity.Policy;
import umc.teamc.youthStepUp.policy.exception.PolicyErrorCode;
import umc.teamc.youthStepUp.policy.exception.PolicyException;
import umc.teamc.youthStepUp.policy.repository.PolicyRepository;

@Service
@RequiredArgsConstructor
public class PolicyServiceImpl implements PolicyService {

    private final PolicyRepository policyRepository;
    private final MemberRepository memberRepository;
    private final BookmarkPolicyRepository bookmarkPolicyRepository;

    @Override
    public Policy createPolicy(PolicyBookmarkRequestDTO.BookmarkRequestDTO requestDTO) {
        //이미 존재하는 policy인지 확인해야함. 존재하는 Policy라면 그것을 return하고 아니면 저장하고 저장한 Policy를 리턴
        Policy existingPolicy = policyRepository.findBySrchPolicyId(requestDTO.getSrchPolicyId());
        if (existingPolicy != null) {
            return existingPolicy;
        }

        //이미 존재하지 않는 Policy라면 저장한 Policy를 리턴
        PolicyBookmarkRequestDTO.CreatePolicyDTO dto = new PolicyBookmarkRequestDTO.CreatePolicyDTO();
        Policy policy = dto.toEntity(requestDTO);
        return policyRepository.save(policy);
    }

    @Override
    public BookMarkPolicy createBookmark(Long memberId, Long policyId) {

        //회원정보조회
        Member member = memberRepository.findById(memberId).orElseThrow(() ->
                new MemberCustomException(MemberErrorCode.MEMBER_NOT_FOUND));

        //정책정보조회
        Policy policy = policyRepository.findById(policyId).orElseThrow(() ->
                new PolicyException(PolicyErrorCode.POLICY_NOT_FOUND));

        //이미 존재하는 북마크인지 확인 -> 존재하면 이미 존재한다는 예외처리 / 존재하지 않는다면 북마크 객체 생성 후 저장
        BookMarkPolicy existingBookmark = bookmarkPolicyRepository.findBookMarkPolicyByMemberIdAndPolicyId(memberId,
                policyId);
        if (existingBookmark != null) {
            return existingBookmark;
        }

        //북마크 객체 생성
        PolicyBookmarkRequestDTO.CreateBookmarkDTO dto = new PolicyBookmarkRequestDTO.CreateBookmarkDTO();
        BookMarkPolicy bookmark = dto.toEntity(member, policy);

        //DB에 저장
        return bookmarkPolicyRepository.save(bookmark);
    }
}
