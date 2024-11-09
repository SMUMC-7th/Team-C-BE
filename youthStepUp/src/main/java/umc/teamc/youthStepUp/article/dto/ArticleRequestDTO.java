package umc.teamc.youthStepUp.article.dto;

import lombok.Getter;
import umc.teamc.youthStepUp.article.entity.Article;

public class ArticleRequestDTO {

    @Getter
    public static class CreateArticleDTO {

        private String title;
        private String content;

        public Article toEntity() {
            return Article.builder()
                    .title(this.title)
                    .content(this.content)
                    .build();
        }
    }

    @Getter
    public static class UpdateArticleDTO {
        private String title;
        private String content;
    }
}
