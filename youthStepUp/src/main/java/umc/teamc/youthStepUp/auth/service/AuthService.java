package umc.teamc.youthStepUp.auth.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import umc.teamc.youthStepUp.auth.constant.TokenConstant;
import umc.teamc.youthStepUp.auth.dto.NewMemberResponseDTO;
import umc.teamc.youthStepUp.auth.dto.UserTokenDTO;
import umc.teamc.youthStepUp.auth.dto.google.GoogleUserInfoDTO;
import umc.teamc.youthStepUp.auth.dto.kakao.KakaoUserInfoDTO;
import umc.teamc.youthStepUp.auth.dto.naver.NaverUserInfoDTO;
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

    public boolean isLocalhost(HttpServletRequest request) {
        boolean isLocalhost = false;
        String referer = request.getHeader("Referer");
        if (referer != null && referer.contains("localhost")) {
            isLocalhost = true;
        }
        return isLocalhost;
    }

    //kakao
    public NewMemberResponseDTO login(KakaoUserInfoDTO infoDTO, HttpServletResponse response) {
        String socialId = String.valueOf(infoDTO.id());
        boolean isExistMember = memberRepository.existsBySocialId(socialId);
        Member member = null;
        if (!isExistMember) {
            member = createNewMember(infoDTO.kakaoAccount().profile().nickname(),
                    infoDTO.kakaoAccount().profile().profileImageUrl(), socialId);
            getResponse(member, response);
        } else {
            member = memberRepository.findBySocialId(socialId).orElseThrow(
                    () -> new MemberCustomException(MemberErrorCode.MEMBER_NOT_FOUND));
            getResponse(member, response);
        }
        return new NewMemberResponseDTO(isExistMember, member.getId(), member.getNickName(), member.getImgUrl());
    }

    //naver
    public NewMemberResponseDTO login(NaverUserInfoDTO infoDTO, HttpServletResponse response) {
        String socialId = infoDTO.response().id();
        boolean isExistMember = memberRepository.existsBySocialId(socialId);
        Member member = null;
        if (!isExistMember) {
            member = createNewMember(infoDTO.response().name(), infoDTO.response().profile_image(),
                    socialId);
            getResponse(member, response);
        } else {
            member = memberRepository.findBySocialId(socialId).orElseThrow(
                    () -> new MemberCustomException(MemberErrorCode.MEMBER_NOT_FOUND));
            getResponse(member, response);
        }
        return new NewMemberResponseDTO(isExistMember, member.getId(), member.getNickName(), member.getImgUrl());
    }

    public NewMemberResponseDTO login(GoogleUserInfoDTO infoDTO, HttpServletResponse response) {
        String socialId = infoDTO.id();
        boolean isExistMember = memberRepository.existsBySocialId(socialId);
        Member member = null;
        if (!isExistMember) {
            member = createNewMember(infoDTO.name(), infoDTO.picture(),
                    socialId);
            getResponse(member, response);
        } else {
            member = memberRepository.findBySocialId(socialId).orElseThrow(
                    () -> new MemberCustomException(MemberErrorCode.MEMBER_NOT_FOUND));
            getResponse(member, response);
        }
        return new NewMemberResponseDTO(isExistMember, member.getId(), member.getNickName(), member.getImgUrl());
    }

    private String newRandomNickName(String originName) {
        int randomNumber = (int) (Math.random() * 10000); // 0부터 9999까지의 랜덤 숫자 생성
        return originName + "의청춘" + randomNumber;
    }

    private Member createNewMember(String name, String profileImgUrl, String socialId) {
        Member member = Member.builder()
                .nickName(newRandomNickName(name))
                .imgUrl(profileImgUrl)
                .socialId(socialId)
                .build();
        memberRepository.save(member);
        return member;
    }

    private void getResponse(Member member, HttpServletResponse response) {
        Long id = member.getId();
        ResponseCookie refreshCookie = jwtProvider.createCookie("refreshToken", id);
        ResponseCookie accessCookie = jwtProvider.createCookie("accessToken", id);
        setAuthentication(accessCookie.getValue());
        setResponse(response, refreshCookie, accessCookie);
    }

    public void reissueToken(String refreshToken, HttpServletResponse response) {
        validateRefreshToken(refreshToken);
        Long id = Long.parseLong(jwtProvider.getUserId(refreshToken));
        String accessToken = jwtProvider.createAccessToken(id);
        ResponseCookie refreshCookie = jwtProvider.createCookie("refreshToken", id);
        ResponseCookie accessCookie = jwtProvider.createCookie("accessToken", id);
        setAuthentication(accessToken);
        setResponse(response, refreshCookie, accessCookie);
    }

    private static void setResponse(HttpServletResponse response, ResponseCookie refreshCookie,
                                    ResponseCookie accessCookie) {
        response.addHeader("Set-Cookie", refreshCookie.toString());
        response.addHeader("Set-Cookie", accessCookie.toString());
    }

    public void setAuthentication(String accessToken) {
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

    public void logout(HttpServletResponse response, HttpServletRequest request) {
        Cookie accessToken = jwtProvider.findCookie(request.getCookies(), TokenConstant.ACCESS_TOKEN.getValue());
        Cookie refreshToken = jwtProvider.findCookie(request.getCookies(), TokenConstant.REFRESH_TOKEN.getValue());
        setCookieClean(accessToken);
        setCookieClean(refreshToken);
        response.addCookie(accessToken);
        response.addCookie(refreshToken);
    }

    private static void setCookieClean(Cookie cookie) {
        cookie.setPath("/");
        cookie.setMaxAge(0);
        cookie.setPath("/");
    }

    public void getDeviceToken(Long id, UserTokenDTO dto) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new MemberCustomException(MemberErrorCode.MEMBER_NOT_FOUND)
        );
        member.editSocialId(dto.deviceToken());
    }

}
