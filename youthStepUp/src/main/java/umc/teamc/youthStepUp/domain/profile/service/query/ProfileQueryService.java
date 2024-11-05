package umc.teamc.youthStepUp.domain.profile.service.query;

import org.springframework.data.domain.Slice;
import umc.teamc.youthStepUp.domain.profile.entity.Bookmark;
import umc.teamc.youthStepUp.member.entity.Member;

public interface ProfileQueryService {
    public Member getProfile(Long profileId);

    public Slice<Bookmark> getBookmarks(Long cursor, int offset, Long memberId);
}
