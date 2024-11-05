package umc.teamc.youthStepUp.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.teamc.youthStepUp.auth.dto.KakaoUserInfoDTO;
import umc.teamc.youthStepUp.auth.dto.LoginResponseDTO;
import umc.teamc.youthStepUp.auth.jwt.JwtProvider;
import umc.teamc.youthStepUp.member.entity.Member;
import umc.teamc.youthStepUp.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    public LoginResponseDTO createNewMember(KakaoUserInfoDTO infoDTO) {
        Member member = Member.builder()
                .nickName(infoDTO.kakaoAccount().profile().nickname())
                .kakaoId(infoDTO.id())
                .build();
        memberRepository.save(member);
        return jwtProvider.getToken(member.getId());
    }


}
