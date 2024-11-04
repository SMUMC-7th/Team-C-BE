package umc.teamc.youthStepUp.domain.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.teamc.youthStepUp.domain.calendar.entity.Bookmark;

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


}
