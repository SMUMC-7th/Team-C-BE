package umc.teamc.youthStepUp.domain.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.teamc.youthStepUp.member.entity.Member;

public interface ProfileRepository extends JpaRepository<Member, Long> {
}