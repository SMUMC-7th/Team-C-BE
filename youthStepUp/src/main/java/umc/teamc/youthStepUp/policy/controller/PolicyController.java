package umc.teamc.youthStepUp.policy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.xml.bind.JAXBException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import umc.teamc.youthStepUp.global.apiPayload.CustomResponse;
import umc.teamc.youthStepUp.global.success.GeneralSuccessCode;
import umc.teamc.youthStepUp.policy.dto.PolicyDetailRequest;
import umc.teamc.youthStepUp.policy.service.PolicyDetailService;

@RestController
@RequiredArgsConstructor
@Tag(name = "정책 조회 관련 컨트롤러")
public class PolicyController {

    private final PolicyDetailService policyDetailService;

    @GetMapping("/policy/recommend/{srchPolicyId}")
    @Operation(summary = "정책 id로 정책 상세 조회", description = "정책 id를 통해서 해당 정책 상세 조회 API")
    public CustomResponse<?> getPolicyDetail(@PathVariable("srchPolicyId") String srchPolicyId) throws JAXBException
    {
        PolicyDetailRequest policyDetailRequest = policyDetailService.callAPI(srchPolicyId, null, null, null, null);
        return CustomResponse.onSuccess(GeneralSuccessCode.OK, policyDetailRequest);
    }
}
