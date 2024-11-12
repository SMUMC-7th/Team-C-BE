package umc.teamc.youthStepUp.policyInfo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import umc.teamc.youthStepUp.policyInfo.entity.PolicyInfo;
import umc.teamc.youthStepUp.policyInfo.exception.PolicyInfoException;
import umc.teamc.youthStepUp.policyInfo.repository.PolicyInfoRepository;

@Service
@RequiredArgsConstructor
public class PolicyInfoServiceImpl implements PolicyInfoService{

    private final PolicyInfoRepository policyInfoRepository;
    @Override
    public Page<PolicyInfo> getPolicyInfo(int pageNumber, int pageSize) throws PolicyInfoException {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return policyInfoRepository.findAll(pageable);
    }
}
