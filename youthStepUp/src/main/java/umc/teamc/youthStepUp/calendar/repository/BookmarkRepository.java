package umc.teamc.youthStepUp.calendar.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.teamc.youthStepUp.calendar.entity.Bookmark;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    @Query("SELECT b FROM Bookmark b " +
            "WHERE :date BETWEEN " +
            "SUBSTRING(b.policyPeriod, 1, 10) AND SUBSTRING(b.policyPeriod, 12, 10)")
    List<Bookmark> findByDateWithinPolicyPeriod(@Param("date") String date);

    @Query("SELECT b FROM Bookmark b " +
            "WHERE :month BETWEEN " +
            "SUBSTRING(b.policyPeriod, 1, 7) AND SUBSTRING(b.policyPeriod, 12, 7)")
    List<Bookmark> findByMonthWithinPolicyPeriod(@Param("month") String month);

    // 커서가 없을 때 처음부터 조회 (최신 순)
    Slice<Bookmark> findByMemberIdOrderByIdDesc(Long memberId, Pageable pageable);

    // 커서가 있을 때 해당 커서 이전 데이터 조회 (최신 순)
    Slice<Bookmark> findByMemberIdAndIdLessThanOrderByIdDesc(Long memberId, Long cursorId, Pageable pageable);

}
