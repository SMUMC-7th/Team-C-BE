package umc.teamc.youthStepUp.article.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.domain.Slice;
import umc.teamc.youthStepUp.article.entity.Article;
import umc.teamc.youthStepUp.auth.annotation.MemberInfo;
import umc.teamc.youthStepUp.member.dto.MemberDTO.MemberDataDTO;
import umc.teamc.youthStepUp.member.entity.Member;

import java.time.LocalDateTime;
import java.util.List;

public class ArticleResponseDTO {

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class CreatedArticleResponseDTO { // 게시판 만들고 나서 응답

        private Long articleId;
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime createdAt;
        private String nickname;
        private Long memberId;

        public static CreatedArticleResponseDTO from(Article article, @MemberInfo Long memberId, String nickName) {
            return CreatedArticleResponseDTO.builder()
                    .articleId(article.getId())
                    .createdAt(article.getCreatedAt())
                    .nickname(nickName)
                    .memberId(memberId)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class ArticlePreviewDTO {

        private Long articleId;
        private String title;
        private String content;
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime createdAt;
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime updatedAt;
        private MemberDataDTO memberDataDTO;

        public static ArticlePreviewDTO from(Article article, MemberDataDTO memberDataDTO) {

            return ArticlePreviewDTO.builder()
                    .articleId(article.getId())
                    .title(article.getTitle())
                    .memberDataDTO(memberDataDTO)
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
    public static class ArticlePagePreviewListDTO { // 커뮤니티 글 여러 개 보이는

        private List<ArticlePreviewDTO> articleList;
        private Long nextCursorId;

        public static ArticlePagePreviewListDTO from(Slice<Article> articles) {

            List<ArticlePreviewDTO> articleList = articles.getContent()
                    .stream()
                    .map(article -> ArticlePreviewDTO.from(article, createMemberDTO(article.getMember())))
                    .toList();

            Long nextCursorId = articles.hasNext()
                    ? articles.getContent()
                    .get(articles.getNumberOfElements() - 1)
                    .getId() : null;

            return ArticlePagePreviewListDTO.builder()
                    .articleList(articleList)
                    .nextCursorId(nextCursorId)
                    .build();
        }
    }


    public static MemberDataDTO createMemberDTO(Member member) {
        return new MemberDataDTO(member.getNickName(), member.getId());
    }


    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class UpdatedArticleDTO {

        private Long articleId;
        private String title;
        private String content;
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime createdAt;
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
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
