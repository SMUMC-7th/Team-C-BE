package umc.teamc.youthStepUp.member.dto;

import lombok.Getter;

public class MemberDTO {

    @Getter
    public static class MemberDataDTO {

        private String nickname;
        private Long memberId;

        public MemberDataDTO(String nickname, Long memberId) {
            this.nickname = nickname;
            this.memberId = memberId;
        }
    }
}
