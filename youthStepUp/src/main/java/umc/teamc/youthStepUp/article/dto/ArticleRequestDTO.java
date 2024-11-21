package umc.teamc.youthStepUp.article.dto;

import lombok.Getter;
import umc.teamc.youthStepUp.article.entity.Article;
import umc.teamc.youthStepUp.member.entity.Member;

public class ArticleRequestDTO {

    @Getter
    public static class CreateArticleDTO {

        private String title;
        private String content;

        public Article toEntity(Member member) {
            return Article.builder()
                    .title(this.title)
                    .content(this.content)
                    .member(member)
                    .build();
        }
    }

    @Getter
    public static class UpdateArticleDTO {
        private String title;
        private String content;
    }
}
