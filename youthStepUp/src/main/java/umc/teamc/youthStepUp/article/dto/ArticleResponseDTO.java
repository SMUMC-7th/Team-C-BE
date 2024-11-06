package umc.teamc.youthStepUp.article.dto;

import lombok.*;
import org.springframework.data.domain.Page;
import umc.teamc.youthStepUp.article.entity.Article;

import java.time.LocalDateTime;
import java.util.List;

public class ArticleResponseDTO {

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class CreatedArticleResponseDTO { // 게시판 만들고 나서 응답

        private Long articleId;
        private LocalDateTime createdAt;

        public static CreatedArticleResponseDTO from(Article article) {
            return CreatedArticleResponseDTO.builder()
                    .articleId(article.getId())
                    .createdAt(article.getCreatedAt())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class ArticlePreviewDTO {

        private Long articleId;
        private Long articleMemberId;
        private String title;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public static ArticlePreviewDTO from(Article article) {
            return ArticlePreviewDTO.builder()
                    .articleId(article.getId())
                    .articleMemberId(article.getMember().getId())
                    .title(article.getTitle())
                    .content(article.getContent())
                    .createdAt(article.getCreatedAt())
                    .updatedAt(article.getUpdatedAt())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class ArticlePagePreviewListDTO {

        private List<ArticlePreviewDTO> articleList;
        private int pageSize;
        private int pageNumber;
        private long totalPage;

        public static ArticlePagePreviewListDTO from(Page<Article> articles) {

            List<ArticlePreviewDTO> articleList = articles.getContent()
                    .stream()
                    .map(ArticlePreviewDTO::from)
                    .toList();

            return ArticlePagePreviewListDTO.builder()
                    .articleList(articleList)
                    .pageSize(articles.getSize())
                    .pageNumber(articles.getNumber() + 1)
                    .totalPage(articles.getTotalPages())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class UpdatedArticleDTO {

        private Long articleId;
        private String title;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public static ArticlePreviewDTO from(Article article) {
            return ArticlePreviewDTO.builder()
                    .articleId(article.getId())
                    .title(article.getTitle())
                    .content(article.getContent())
                    .createdAt(article.getCreatedAt())
                    .updatedAt(article.getUpdatedAt())
                    .build();
        }
    }

}
