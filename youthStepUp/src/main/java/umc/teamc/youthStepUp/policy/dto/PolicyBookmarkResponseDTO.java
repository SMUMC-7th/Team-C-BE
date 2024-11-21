package umc.teamc.youthStepUp.policy.dto;

import lombok.*;
import umc.teamc.youthStepUp.policy.entity.BookMarkPolicy;

import java.time.LocalDateTime;

public class PolicyBookmarkResponseDTO {

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreateBookmarkResponseDTO {
        private Long policyId;
        private String srchPolicyId;
        private Long memberId;
        private LocalDateTime createdAt;

        public static CreateBookmarkResponseDTO from(BookMarkPolicy bookMarkPolicy) {
            return CreateBookmarkResponseDTO.builder()
                    .policyId(bookMarkPolicy.getId())
                    .srchPolicyId(bookMarkPolicy.getPolicy().getSrchPolicyId())
                    .memberId(bookMarkPolicy.getMember().getId())
                    .createdAt(bookMarkPolicy.getCreatedAt())
                    .build();
        }
    }

}
