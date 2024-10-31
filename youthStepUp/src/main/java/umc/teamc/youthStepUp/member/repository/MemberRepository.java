package umc.teamc.youthStepUp.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.teamc.youthStepUp.member.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
