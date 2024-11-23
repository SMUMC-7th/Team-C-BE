package umc.teamc.youthStepUp.policy.service;

import jakarta.xml.bind.JAXBException;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import umc.teamc.youthStepUp.policy.dto.PolicyDetailRequest;

@Service
@RequiredArgsConstructor
public class PolicyDetailService {

    private final AuthenticationService authenticationService;
    private final RestTemplate restTemplate;
    private static final String SERVER_URL = "https://www.youthcenter.go.kr/opi/youthPlcyList.do";

    public PolicyDetailRequest callAPI(String srchPolicyId, String query, String bizTycdSel, String srchPolyBizSecd,
                                       String keyword) throws JAXBException {
        String token = authenticationService.getAuthToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        // 쿼리파라미터
        Map<String, String> params = new HashMap<>();
        params.put("openApiVlak", token);
        params.put("display", "1");
        params.put("pageIndex", "1");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("?openApiVlak={openApiVlak}&display={display}&pageIndex={pageIndex}");

        if (srchPolicyId != null && !srchPolicyId.isEmpty()) {
            params.put("srchPolicyId", srchPolicyId);
            stringBuilder.append("&srchPolicyId={srchPolicyId}");
        }
        if (query != null && !query.isEmpty()) {
            params.put("query", query);
            stringBuilder.append("&query={query}");
        }
        if (bizTycdSel != null && !bizTycdSel.isEmpty()) {
            params.put("bizTycdSel", bizTycdSel);
            stringBuilder.append("&bizTycdSel={bizTycdSel}");
        }
        if (srchPolyBizSecd != null && !srchPolyBizSecd.isEmpty()) {
            params.put("srchPolyBizSecd", srchPolyBizSecd);
            stringBuilder.append("&srchPolyBizSecd={srchPolyBizSecd}");
        }
        if (keyword != null && !keyword.isEmpty()) {
            params.put("keyword", keyword);
            stringBuilder.append("&keyword={keyword}");
        }

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                SERVER_URL + stringBuilder,
                HttpMethod.GET,
                entity,
                String.class,
                params
        );
        return PolicyDetailRequest.unmarshal(response.getBody());
    }
}
