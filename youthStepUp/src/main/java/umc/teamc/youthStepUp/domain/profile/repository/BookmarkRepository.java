package umc.teamc.youthStepUp.domain.profile.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.teamc.youthStepUp.domain.profile.entity.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    // 커서가 없을 때 처음부터 조회 (최신 순)
    Slice<Bookmark> findByMemberIdOrderByIdDesc(Long memberId, Pageable pageable);

    // 커서가 있을 때 해당 커서 이전 데이터 조회 (최신 순)
    Slice<Bookmark> findByMemberIdAndIdLessThanOrderByIdDesc(Long memberId, Long cursorId, Pageable pageable);

}
