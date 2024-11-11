package umc.teamc.youthStepUp.policyInfo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.teamc.youthStepUp.global.apiPayload.CustomResponse;
import umc.teamc.youthStepUp.global.success.GeneralSuccessCode;
import umc.teamc.youthStepUp.policyInfo.dto.PolicyInfoResponseDTO;
import umc.teamc.youthStepUp.policyInfo.entity.PolicyInfo;
import umc.teamc.youthStepUp.policyInfo.service.PolicyInfoService;

@RestController
@RequiredArgsConstructor
@Tag(name = "정책 용어 소개 관련 API")
public class PolicyInfoController {
    private final PolicyInfoService policyInfoService;

    @GetMapping("/policy/info")
    @Operation(method = "GET", summary = "정책 용어 소개 조회 API")
    public CustomResponse<?> getPolicyInfoUsingOffset(@RequestParam(defaultValue = "0") int pageNumber,
                                                      @RequestParam(defaultValue = "1") int pageSize) {
        Page<PolicyInfo> policyInfos = policyInfoService.getPolicyInfo(pageNumber, pageSize);
        return CustomResponse.onSuccess(GeneralSuccessCode.OK, PolicyInfoResponseDTO.PolicyInfoPagePreviewListDTO.from(policyInfos));
    }
}
