package umc.teamc.youthStepUp.policy.dto;

import lombok.Getter;
import umc.teamc.youthStepUp.member.entity.Member;
import umc.teamc.youthStepUp.policy.entity.BookMarkPolicy;
import umc.teamc.youthStepUp.policy.entity.Policy;

import java.time.LocalDate;

public class PolicyBookmarkRequestDTO {

    @Getter
    public static class CreateBookmarkDTO {
        public BookMarkPolicy toEntity(Member member, Policy policy) {
            return BookMarkPolicy.builder()
                    .member(member)
                    .policy(policy)
                    .isCompleted(false)
                    .build();
        }
    }

    @Getter
    public static class CreatePolicyDTO {
        public Policy toEntity(PolicyBookmarkRequestDTO.BookmarkRequestDTO requestDTO) {
            return Policy.builder()
                    .polyBizSjnm(requestDTO.getPolyBizSjnm())
                    .srchPolicyId(requestDTO.getSrchPolicyId())
                    .startDate(requestDTO.getStartDate())
                    .deadline(requestDTO.getDeadline())
                    .build();
        }
    }

    @Getter
    public static class BookmarkRequestDTO {
        String polyBizSjnm; //정책이름
        String srchPolicyId; //정책Id
        LocalDate startDate; //모집시작
        LocalDate deadline; //모집마감일
    }
}
