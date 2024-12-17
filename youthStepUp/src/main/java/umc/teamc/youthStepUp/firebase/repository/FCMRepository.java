package umc.teamc.youthStepUp.firebase.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.teamc.youthStepUp.firebase.domain.Alarm;

public interface FCMRepository extends JpaRepository<Alarm, Long> {
    @Query("SELECT a " +
            "FROM Alarm a " +
            "WHERE a.member.id = :memberId " +
            "AND (:lastId IS NULL OR a.id < :lastId) " +
            "ORDER BY a.createdAt DESC")
    Slice<Alarm> findAllByMemberIdAndIdLessThanOrderByCreatedAtDesc(
            @Param("memberId") Long memberId,
            @Param("lastId") Long lastId,
            Pageable pageable
    );
}
