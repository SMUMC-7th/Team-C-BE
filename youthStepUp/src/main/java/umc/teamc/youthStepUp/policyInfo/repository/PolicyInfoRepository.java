package umc.teamc.youthStepUp.policyInfo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.teamc.youthStepUp.policyInfo.entity.PolicyInfo;

public interface PolicyInfoRepository extends JpaRepository<PolicyInfo, Long> {
    Page<PolicyInfo> findAll(Pageable pageable);
}
