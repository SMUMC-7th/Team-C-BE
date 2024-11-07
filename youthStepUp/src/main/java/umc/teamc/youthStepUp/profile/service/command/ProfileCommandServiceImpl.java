package umc.teamc.youthStepUp.profile.service.command;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.teamc.youthStepUp.calendar.entity.Bookmark;
import umc.teamc.youthStepUp.calendar.repository.BookmarkRepository;
import umc.teamc.youthStepUp.member.entity.Member;
import umc.teamc.youthStepUp.profile.dto.request.UpdateProfileRequestDTO;
import umc.teamc.youthStepUp.profile.exception.BookmarkErrorCode;
import umc.teamc.youthStepUp.profile.exception.BookmarkException;
import umc.teamc.youthStepUp.profile.exception.ProfileErrorCode;
import umc.teamc.youthStepUp.profile.exception.ProfileException;
import umc.teamc.youthStepUp.profile.repository.ProfileRepository;

@Transactional
@Service
@RequiredArgsConstructor
public class ProfileCommandServiceImpl implements ProfileCommandService {

    private final ProfileRepository profileRepository;
    private final BookmarkRepository bookmarkRepository;

    @Override
    public Member updateProfile(Long memberId, UpdateProfileRequestDTO request) {
        Member profile = profileRepository.findById(memberId).orElseThrow(() ->
                new ProfileException(ProfileErrorCode.NOT_FOUND));
        //profile.updateProfile(memberId, request); // 프로필 수정 로직을 엔티티에서 수행.
        profileRepository.save(profile);
        return profile;
    }

    @Override
    public void deleteProfile(Long memberId, String name) {
        Member profile = profileRepository.findById(memberId).orElseThrow(() ->
                new ProfileException(ProfileErrorCode.NOT_FOUND));

        if (profile.getNickName().equals(name)) {
            //profile.setDeletedTime(LocalDateTime.now());
        } else {
            throw new ProfileException(ProfileErrorCode.BAD_REQUEST);
        }
    }

    //피그마에는 북마크 제거 버튼 없긴 함, 안 만들 계획 이라면 없애도 될 듯
    @Override
    public void deleteBookmark(Long bookmarkId) {
        Bookmark bookmark = bookmarkRepository.findById(bookmarkId).orElseThrow(() ->
                new BookmarkException(BookmarkErrorCode.NOT_FOUND));

        bookmarkRepository.delete(bookmark);
    }
}
