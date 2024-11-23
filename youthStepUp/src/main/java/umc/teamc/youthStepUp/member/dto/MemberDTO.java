package umc.teamc.youthStepUp.member.dto;

import lombok.Getter;
import umc.teamc.youthStepUp.auth.annotation.MemberIdInfo;

public class MemberDTO {

    @Getter
    public static class MemberDataDTO {

        private String nickName;
        private Long memberId;

        public MemberDataDTO(String nickName, @MemberIdInfo Long memberId) {
            this.nickName = nickName;
            this.memberId = memberId;
        }
    }
}
