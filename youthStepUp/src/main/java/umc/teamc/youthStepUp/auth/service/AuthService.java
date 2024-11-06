package umc.teamc.youthStepUp.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import umc.teamc.youthStepUp.auth.dto.KakaoUserInfoDTO;
import umc.teamc.youthStepUp.auth.dto.TokenResponseDTO;
import umc.teamc.youthStepUp.auth.jwt.JwtProvider;
import umc.teamc.youthStepUp.auth.jwt.error.JwtErrorCode;
import umc.teamc.youthStepUp.auth.jwt.error.exception.JwtException;
import umc.teamc.youthStepUp.member.entity.Member;
import umc.teamc.youthStepUp.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    public TokenResponseDTO createNewMember(KakaoUserInfoDTO infoDTO) {
        Member member = Member.builder()
                .nickName(infoDTO.kakaoAccount().profile().nickname())
                .kakaoId(infoDTO.id())
                .build();
        memberRepository.save(member);
        return jwtProvider.getToken(member.getId());
    }

    public TokenResponseDTO reissueToken(String refreshToken) {
        validateRefreshToken(refreshToken);
        TokenResponseDTO tokenResponseDTO = jwtProvider.getToken(Long.parseLong(jwtProvider.getUserId(refreshToken)));
        setAuthentication(tokenResponseDTO);
        return tokenResponseDTO;
    }

    private void setAuthentication(TokenResponseDTO tokenResponseDTO) {
        Authentication authentication = jwtProvider.getAuthentication(tokenResponseDTO.accessToken());
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
