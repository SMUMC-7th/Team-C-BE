package umc.teamc.youthStepUp.calendar.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.teamc.youthStepUp.calendar.dto.response.BookmarkResponseByDateDTO;
import umc.teamc.youthStepUp.calendar.dto.response.BookmarkResponseByMonthDTO;
import umc.teamc.youthStepUp.policy.entity.BookMarkPolicy;
import umc.teamc.youthStepUp.profile.dto.response.BookmarkResponseDTO;

public interface BookmarkPolicyRepository extends JpaRepository<BookMarkPolicy, Long> {
    BookMarkPolicy findBookMarkPolicyByMemberIdAndPolicyId(Long memberId, Long policyId);

    // 특정 회원 ID와 특정 날짜에 시작 또는 마감된 정책 북마크 조회
    @Query("SELECT new umc.teamc.youthStepUp.calendar.dto.response.BookmarkResponseByDateDTO(b.id,b.policy.polyBizSjnm, b.policy.startDate, b.policy.deadline, b.isCompleted, b.policy.srchPolicyId) "
            +
            "FROM BookMarkPolicy b JOIN b.policy p ON b.policy.id = p.id " +
            "WHERE b.member.id = :memberId " +
            "AND (p.startDate = :date OR p.deadline = :date) " +
            "AND b.deletedAt IS NULL")
    List<BookmarkResponseByDateDTO> findByMemberIdAndDateWithinPolicyPeriod(@Param("memberId") Long memberId,
                                                                            @Param("date") LocalDate date);

    // 특정 회원 ID와 특정 달에 시작 또는 마감된 정책 북마크 조회
    @Query("SELECT new umc.teamc.youthStepUp.calendar.dto.response.BookmarkResponseByMonthDTO(b.id,b.policy.polyBizSjnm, b.policy.startDate, b.policy.deadline) "
            +
            "FROM BookMarkPolicy b JOIN b.policy p ON b.policy.id = p.id " +
            "WHERE b.member.id = :memberId " +
            "AND (YEAR(p.startDate) = YEAR(:month) AND MONTH(p.startDate) = MONTH(:month) " +
            "OR YEAR(p.deadline) = YEAR(:month) AND MONTH(p.deadline) = MONTH(:month)) " +
            "AND b.deletedAt IS NULL")
    List<BookmarkResponseByMonthDTO> findByMemberIdAndMonthWithinPolicyPeriod(@Param("memberId") Long memberId,
                                                                              @Param("month") LocalDate month);


    // 커서가 없을 때 최신순으로 조회 (DTO 반환)
    @Query("SELECT new umc.teamc.youthStepUp.profile.dto.response.BookmarkResponseDTO(b.id, p.srchPolicyId, p.polyBizSjnm) "
            +
            "FROM BookMarkPolicy b " +
            "JOIN b.policy p " +
            "WHERE b.member.id = :memberId " +
            "AND b.deletedAt IS NULL " +
            "ORDER BY b.id DESC")
    Slice<BookmarkResponseDTO> findByMemberIdAndDeletedAtIsNullOrderByIdDesc(@Param("memberId") Long memberId,
                                                                             Pageable pageable);

    // 커서가 있을 때 해당 커서 이전 데이터 최신순으로 조회 (DTO 반환)
    @Query("SELECT new umc.teamc.youthStepUp.profile.dto.response.BookmarkResponseDTO(b.id, p.srchPolicyId, p.polyBizSjnm) "
            +
            "FROM BookMarkPolicy b " +
            "JOIN b.policy p " +
            "WHERE b.member.id = :memberId " +
            "AND b.deletedAt IS NULL " +
            "AND b.id < :cursorId " +
            "ORDER BY b.id DESC")
    Slice<BookmarkResponseDTO> findByMemberIdAndDeletedAtIsNullAndIdLessThanOrderByIdDesc(
            @Param("memberId") Long memberId, @Param("cursorId") Long cursorId, Pageable pageable);

    Boolean existsByPolicyIdAndMemberId(Long policyId, Long memberId);
}
