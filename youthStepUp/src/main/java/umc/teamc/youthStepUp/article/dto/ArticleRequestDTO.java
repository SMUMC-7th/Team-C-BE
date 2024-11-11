package umc.teamc.youthStepUp.article.dto;

import lombok.Getter;
import umc.teamc.youthStepUp.article.entity.Article;
import umc.teamc.youthStepUp.member.entity.Member;

public class ArticleRequestDTO {

    @Getter
    public static class CreateArticleDTO {

        private String title;
        private String content;
        private String nickname;
        private Long memberId;

        public Article toEntity() {
            return Article.builder()
                    .title(this.title)
                    .content(this.content)
                    .member(Member.builder()
                            .nickName(this.nickname)
                            .id(this.memberId)
                            .build())
                    .build();
        }
    }

    @Getter
    public static class UpdateArticleDTO {
        private String title;
        private String content;
    }
}
