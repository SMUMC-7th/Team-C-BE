package umc.teamc.youthStepUp.policyInfo.service;

import org.springframework.data.domain.Page;
import umc.teamc.youthStepUp.policyInfo.entity.PolicyInfo;

public interface PolicyInfoService {
    Page<PolicyInfo> getPolicyInfo(int pageNumber, int pageSize);
}
