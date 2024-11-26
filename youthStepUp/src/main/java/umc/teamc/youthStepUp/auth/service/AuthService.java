package umc.teamc.youthStepUp.auth.service;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import umc.teamc.youthStepUp.auth.dto.KakaoUserInfoDTO;
import umc.teamc.youthStepUp.auth.dto.NewMemberResponseDTO;
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

    public Member createNewMember(KakaoUserInfoDTO infoDTO) {
        Member member = Member.builder()
                .imgUrl(infoDTO.kakaoAccount().profile().profileImageUrl())
                .kakaoId(infoDTO.id())
                .build();
        memberRepository.save(member);
        return member;
    }

    public NewMemberResponseDTO login(KakaoUserInfoDTO infoDTO, HttpServletResponse response) throws IOException {
        boolean isExistMember = memberRepository.existsByKakaoId(infoDTO.id());
        Member member = null;
        if (!isExistMember) {
            member = createNewMember(infoDTO);
            Long id = member.getId();
            ResponseCookie refreshCookie = jwtProvider.createRefreshCookie(id); //리프레쉬 토큰 쿠키에 담는다.
            ResponseCookie accessCookie = jwtProvider.createAccessCookie(id);
            setResponse(response, refreshCookie, accessCookie);
        } else {
            member = memberRepository.findByKakaoId(infoDTO.id()).orElseThrow(
                    () -> new MemberCustomException(MemberErrorCode.MEMBER_NOT_FOUND)
            );
            Long id = member.getId();
            ResponseCookie refreshCookie = jwtProvider.createRefreshCookie(id); //리프레쉬 토큰 쿠키에 담는다.
            ResponseCookie accessCookie = jwtProvider.createAccessCookie(id); //리프레쉬 토큰 쿠키에 담는다.
            setResponse(response, refreshCookie, accessCookie);
        }
        return new NewMemberResponseDTO(member.getId(), member.getImgUrl());
    }


    public void reissueToken(String refreshToken, HttpServletResponse response) {
        validateRefreshToken(refreshToken);
        Long id = Long.parseLong(jwtProvider.getUserId(refreshToken));
        String accessToken = jwtProvider.createAccessToken(id);
        ResponseCookie refreshCookie = jwtProvider.createRefreshCookie(id); //리프레쉬 토큰 쿠키에 담는다.
        ResponseCookie accessCookie = jwtProvider.createAccessCookie(id); //리프레쉬 토큰 쿠키에 담는다.
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


}
