package umc.teamc.youthStepUp.member.dto;

import lombok.Getter;
import umc.teamc.youthStepUp.article.entity.Article;
import umc.teamc.youthStepUp.auth.annotation.MemberIdInfo;
import umc.teamc.youthStepUp.auth.annotation.MemberInfo;
import umc.teamc.youthStepUp.member.entity.Member;

public class MemberDTO {

    @Getter
    public static class MemberDataDTO {

        private String nickName;
        private Long memberId;
        private String imgUrl;

        public MemberDataDTO(Article article, @MemberInfo Member member) {
            this.nickName = article.getMember().getNickName();
            this.memberId = article.getMember().getId();
            this.imgUrl = article.getMember().getImgUrl();
        }
    }
}
