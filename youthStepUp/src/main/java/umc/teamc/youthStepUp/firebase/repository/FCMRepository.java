package umc.teamc.youthStepUp.firebase.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.teamc.youthStepUp.firebase.domain.Alarm;

public interface FCMRepository extends JpaRepository<Alarm, Long> {
    Slice<Alarm> findAllByMemberIdAndIdLessThanOrderByCreatedAtDesc(Long memberId, Long lastId, Pageable pageable);
}
