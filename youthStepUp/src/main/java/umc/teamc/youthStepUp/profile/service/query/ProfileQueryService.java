package umc.teamc.youthStepUp.profile.service.query;

import org.springframework.data.domain.Slice;
import umc.teamc.youthStepUp.calendar.entity.Bookmark;
import umc.teamc.youthStepUp.member.entity.Member;

public interface ProfileQueryService {
    public Member getProfile(Long profileId);

    public Slice<Bookmark> getBookmarks(Long cursor, int offset, Long memberId);
}
