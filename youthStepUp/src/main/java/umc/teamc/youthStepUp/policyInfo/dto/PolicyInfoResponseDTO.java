package umc.teamc.youthStepUp.policyInfo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.domain.Page;
import umc.teamc.youthStepUp.policyInfo.entity.PolicyInfo;

import java.time.LocalDateTime;
import java.util.List;

public class PolicyInfoResponseDTO {
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class PolicyInfoPreviewDTO {

        private Long policyInfoId;
        private String infoTitle;
        private String infoContent;
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime createdAt;

        public static PolicyInfoPreviewDTO from(PolicyInfo policyInfo) {
            return PolicyInfoPreviewDTO.builder()
                    .policyInfoId(policyInfo.getId())
                    .infoTitle(policyInfo.getTitle())
                    .infoContent(policyInfo.getContent())
                    .createdAt(policyInfo.getCreatedAt())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class PolicyInfoPagePreviewListDTO {

        private List<PolicyInfoPreviewDTO> policyInfoList;
        private int pageSize;
        private int pageNumber;
        private long totalPage;

        public static PolicyInfoPagePreviewListDTO from(Page<PolicyInfo> policyInfos) {

            List<PolicyInfoPreviewDTO> policyInfoList = policyInfos.getContent()
                    .stream()
                    .map(PolicyInfoPreviewDTO::from)
                    .toList();

            return PolicyInfoPagePreviewListDTO.builder()
                    .policyInfoList(policyInfoList)
                    .pageSize(policyInfos.getSize())
                    .pageNumber(policyInfos.getNumber())
                    .totalPage(policyInfos.getTotalPages())
                    .build();
        }

    }
}
