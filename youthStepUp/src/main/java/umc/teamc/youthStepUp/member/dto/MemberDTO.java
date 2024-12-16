package umc.teamc.youthStepUp.member.dto;

import lombok.Getter;
import umc.teamc.youthStepUp.auth.annotation.MemberIdInfo;
import umc.teamc.youthStepUp.auth.annotation.MemberInfo;
import umc.teamc.youthStepUp.member.entity.Member;

public class MemberDTO {

    @Getter
    public static class MemberDataDTO {

        private String nickName;
        private Long memberId;
        private String imgUrl;

        public MemberDataDTO(@MemberInfo Member member) {
            this.nickName = member.getNickName();
            this.memberId = member.getId();
            this.imgUrl = member.getImgUrl();
        }
    }
}
