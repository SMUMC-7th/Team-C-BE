package umc.teamc.youthStepUp.auth.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import umc.teamc.youthStepUp.auth.dto.KakaoUserInfoDTO;
import umc.teamc.youthStepUp.auth.jwt.JwtProvider;
import umc.teamc.youthStepUp.auth.jwt.error.JwtErrorCode;
import umc.teamc.youthStepUp.auth.jwt.error.exception.JwtException;
import umc.teamc.youthStepUp.member.entity.Member;
import umc.teamc.youthStepUp.member.error.MemberErrorCode;
import umc.teamc.youthStepUp.member.error.exception.MemberCustomException;
import umc.teamc.youthStepUp.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    public void createNewMember(KakaoUserInfoDTO infoDTO) {
        Member member = Member.builder()
                .nickName(infoDTO.kakaoAccount().profile().nickname())
                .kakaoId(infoDTO.id())
                .build();
        memberRepository.save(member);
    }

    public void login(KakaoUserInfoDTO infoDTO, HttpServletResponse response) {
        boolean isExistMember = memberRepository.existsByKakaoId(infoDTO.id());
        if (!isExistMember) {
            createNewMember(infoDTO);
        }
        Long userId = memberRepository.findByKakaoId(infoDTO.id()).orElseThrow(
                () -> new MemberCustomException(MemberErrorCode.MEMBER_NOT_FOUND)
        ).getId();
        Cookie refreshCookie = jwtProvider.createRefreshCookie(userId); //리프레쉬 토큰 쿠키에 담는다.
        Cookie accessCookie = jwtProvider.createAccessCookie(userId); //리프레쉬 토큰 쿠키에 담는다.
        setResponse(response, refreshCookie, accessCookie);
    }

    public void reissueToken(String refreshToken, HttpServletResponse response) {
        validateRefreshToken(refreshToken);
        Long id = Long.parseLong(jwtProvider.getUserId(refreshToken));
        String accessToken = jwtProvider.createAccessToken(id);
        Cookie refreshCookie = jwtProvider.createRefreshCookie(id); //리프레쉬 토큰 쿠키에 담는다.
        Cookie accessCookie = jwtProvider.createAccessCookie(id); //리프레쉬 토큰 쿠키에 담는다.
        setAuthentication(accessToken);
        setResponse(response, refreshCookie, accessCookie);
    }

    private static void setResponse(HttpServletResponse response, Cookie accessCookie, Cookie refreshCookie) {
        response.addCookie(accessCookie);
        response.addCookie(refreshCookie);
    }

    private void setAuthentication(String accessToken) {
        Authentication authentication = jwtProvider.getAuthentication(accessToken);
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authentication);
    }

    private void validateRefreshToken(String refreshToken) {
        if (refreshToken.isEmpty() || refreshToken.isBlank() || refreshToken == null) {
            throw new JwtException(JwtErrorCode.HEADER_NO_TOKEN);
        }
        if (jwtProvider.isExpired(refreshToken)) {
            throw new JwtException(JwtErrorCode.TOKEN_EXPIRED);
        }
        if (!memberRepository.existsById(Long.parseLong(jwtProvider.getUserId(refreshToken)))) {
            throw new JwtException(JwtErrorCode.TOKEN_NO_AUTH);
        }
    }


}
