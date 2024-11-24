package umc.teamc.youthStepUp.policy.service;

import jakarta.xml.bind.JAXBException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import umc.teamc.youthStepUp.policy.dto.PolicyListRequest;

@Service
@RequiredArgsConstructor
public class PolicyRecommendedService {
    private final AuthenticationService authenticationService;
    private final RestTemplate restTemplate;
    private static final String SERVER_URL = "https://www.youthcenter.go.kr/opi/youthPlcyList.do";

    public PolicyListRequest callRecommendAPI(List<String> bizTycdSel,
                                              List<String> srchPolyBizSecd, String display, String pageIndex)
            throws JAXBException {
        String token = authenticationService.getAuthToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        // 쿼리파라미터
        Map<String, String> params = new HashMap<>();
        params.put("openApiVlak", token);
        params.put("display", display);
        params.put("pageIndex", pageIndex);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("?openApiVlak={openApiVlak}&display={display}&pageIndex={pageIndex}");

        if (bizTycdSel != null && !bizTycdSel.isEmpty()) {
            String formattedBizTycdSel = formatCodeList(bizTycdSel);
            params.put("bizTycdSel", formattedBizTycdSel);
            stringBuilder.append("&bizTycdSel=").append(formattedBizTycdSel);
        }
        if (srchPolyBizSecd != null && !srchPolyBizSecd.isEmpty()) {
            String formattedCode = formatCodeList(srchPolyBizSecd);
            params.put("srchPolyBizSecd", formattedCode);
            stringBuilder.append("&srchPolyBizSecd=").append(formattedCode);
        }

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                SERVER_URL + stringBuilder,
                HttpMethod.GET,
                entity,
                String.class,
                params
        );
        return PolicyListRequest.unmarshal(response.getBody());
    }

    private static String formatCodeList(List<String> bizTycdSel) {
        if (bizTycdSel == null || bizTycdSel.isEmpty()) {
            return "";
        }
        if (bizTycdSel.size() == 1) {
            return bizTycdSel.get(0);
        }
        return String.join(",", bizTycdSel);
    }

}
