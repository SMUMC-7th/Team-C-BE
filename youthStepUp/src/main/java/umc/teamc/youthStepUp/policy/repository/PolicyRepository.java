package umc.teamc.youthStepUp.policy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.teamc.youthStepUp.policy.entity.Policy;

public interface PolicyRepository extends JpaRepository<Policy, Long> {

    Policy findBySrchPolicyId(String getSrchPolicyId);

}
