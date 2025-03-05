package umc.teamc.youthStepUp.policy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.xml.bind.JAXBException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.teamc.youthStepUp.auth.annotation.MemberIdInfo;
import umc.teamc.youthStepUp.auth.annotation.MemberInfo;
import umc.teamc.youthStepUp.global.apiPayload.CustomResponse;
import umc.teamc.youthStepUp.global.success.GeneralSuccessCode;
import umc.teamc.youthStepUp.member.dto.MemberKeyWordDTO;
import umc.teamc.youthStepUp.member.entity.Member;
import umc.teamc.youthStepUp.member.service.MemberService;
import umc.teamc.youthStepUp.policy.dto.PolicyBookmarkRequestDTO;
import umc.teamc.youthStepUp.policy.dto.PolicyBookmarkResponseDTO;
import umc.teamc.youthStepUp.policy.dto.PolicyDetailRequest;
import umc.teamc.youthStepUp.policy.dto.PolicyListRequest;
import umc.teamc.youthStepUp.policy.entity.BookMarkPolicy;
import umc.teamc.youthStepUp.policy.entity.Policy;
import umc.teamc.youthStepUp.policy.service.PolicyDetailService;
import umc.teamc.youthStepUp.policy.service.PolicyRandomService;
import umc.teamc.youthStepUp.policy.service.PolicyRecommendedService;
import umc.teamc.youthStepUp.policy.service.PolicyService;

@RestController
@RequiredArgsConstructor
@Tag(name = "정책 조회 관련 컨트롤러")
public class PolicyController {

    private final PolicyDetailService policyDetailService;
    private final PolicyRandomService policyRandomService;
    private final PolicyService policyService;
    private final MemberService memberService;
    private final PolicyRecommendedService recommendedService;

    @GetMapping("/policy/recommend")
    @Operation(summary = "정책 맞춤 추천 조회", description = "정책을 회원 정보에 맞춰 추천받아 조회하는 API")
    @Parameters({
            @Parameter(name = "display", description = "한 페이지에 보이게 될 정책 개수를 입력해주시면 됩니다."),
            @Parameter(name = "pageIndex", description = "조회하고싶은 페이지 번호(1 이상)를 입력해주시면 됩니다. ")
    })
    public CustomResponse<?> callOpenApi(
            @Parameter(hidden = true) @MemberIdInfo Long id,
            @RequestParam(required = false, defaultValue = "10") String display,
            @RequestParam(required = false, defaultValue = "1") String pageIndex
    ) throws JAXBException {
        MemberKeyWordDTO dto = memberService.getKeywords(id);
        PolicyListRequest policyListRequest = recommendedService.callRecommendAPI(dto.keywords(),
                dto.regions(),
                display, pageIndex);
        return CustomResponse.onSuccess(GeneralSuccessCode.OK, policyListRequest);
    }

    @GetMapping("/policy/recommend/{srchPolicyId}")
    @Operation(summary = "정책 id로 정책 상세 조회", description = "정책 id를 통해서 해당 정책 상세 조회 API")
    @Parameters({
            @Parameter(name = "srchPolicyId", description = "조회하고자 하는 정책 아이디(srchPolicyId)를 입력하세요")
    })
    public CustomResponse<?> getPolicyDetail(@PathVariable("srchPolicyId") String srchPolicyId) throws JAXBException {
        PolicyDetailRequest policyDetailRequest = policyDetailService.callAPI(srchPolicyId, null, null, null, null);
        return CustomResponse.onSuccess(GeneralSuccessCode.OK, policyDetailRequest);
    }

    @GetMapping("/policy/recommend/random")
    @Operation(summary = "정책 랜덤 추천 조회", description = "정책을 랜덤으로 추천받아 조회하는 API")
    @Parameters({
            @Parameter(name = "display", description = "한 페이지에 보이게 될 정책 개수를 입력해주시면 됩니다."),
            @Parameter(name = "pageIndex", description = "조회하고싶은 페이지 번호(1 이상)를 입력해주시면 됩니다. ")
    })
    public CustomResponse<?> callOpenApi(
//            @RequestParam(required = false) String srchPolicyId,
//            @RequestParam(required = false) String query,
//            @RequestParam(required = false) String bizTycdSel,
//            @RequestParam(required = false) String srchPolyBizSecd,
//            @RequestParam(required = false) String keyword,
            @RequestParam(required = false, defaultValue = "10") String display,
            @RequestParam(required = false, defaultValue = "1") String pageIndex
    ) throws JAXBException {
//        PolicyListRequest policyListRequest = policyRandomService.callAPI(srchPolicyId, query, bizTycdSel,
//                srchPolyBizSecd, keyword, display, pageIndex);
        PolicyListRequest policyListRequest = policyRandomService.callAPI(null, null, null,
                null, null, display, pageIndex);
        return CustomResponse.onSuccess(GeneralSuccessCode.OK, policyListRequest);
    }

    @PostMapping("/policy/bookmark/request")
    @Operation(summary = "정책 북마크 요청", description = "정책 상세 페이지에서 북마크 요청을 보내는 API")
    public CustomResponse<?> bookmarkRequest(@Parameter(hidden = true) @MemberIdInfo Long id,
                                             @RequestBody PolicyBookmarkRequestDTO.BookmarkRequestDTO requestDTO) {
        Policy newPolicy = policyService.createPolicy(requestDTO);
        Long policyId = newPolicy.getId();
        BookMarkPolicy bookMarkPolicy = policyService.createBookmark(id, policyId);

        return CustomResponse.onSuccess(GeneralSuccessCode.CREATED,
                PolicyBookmarkResponseDTO.CreateBookmarkResponseDTO.from(bookMarkPolicy));
    }

    @GetMapping("/policy/bookmark/exist-check/{srchPolicyId}")
    @Operation(summary = "북마크에 존재하는지 확인 요청", description = "북마크에 특정 정책이 존재하는지 확인하는 요청을 보내는 API")
    @Parameters({
            @Parameter(name = "srchPolicyId", description = "북마크가 되어있는지 확인하고자 하는 정책의 srchPolicyId를 입력해주세요"),
    })
    public CustomResponse<?> bookmarkExistCheck(@Parameter(hidden = true) @MemberInfo Member member,
                                                @PathVariable("srchPolicyId") String srchPolicyId) {
        Boolean result = policyService.isExistBookmark(member.getId(), srchPolicyId);
        return CustomResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

}
