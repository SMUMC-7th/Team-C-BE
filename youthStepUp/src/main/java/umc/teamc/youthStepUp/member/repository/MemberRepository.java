package umc.teamc.youthStepUp.member.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.teamc.youthStepUp.member.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByKakaoId(Long kakaoId);

    Optional<Member> findByKakaoId(Long kakaoId);

}