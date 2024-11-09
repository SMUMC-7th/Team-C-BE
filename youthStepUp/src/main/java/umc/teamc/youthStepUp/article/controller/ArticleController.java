package umc.teamc.youthStepUp.article.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;
import umc.teamc.youthStepUp.article.dto.ArticleRequestDTO;
import umc.teamc.youthStepUp.article.dto.ArticleResponseDTO;
import umc.teamc.youthStepUp.article.entity.Article;
import umc.teamc.youthStepUp.article.service.command.ArticleCommandService;
import umc.teamc.youthStepUp.article.service.query.ArticleQueryService;
import umc.teamc.youthStepUp.global.apiPayload.CustomResponse;
import umc.teamc.youthStepUp.global.success.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@Tag(name = "커뮤니티 API 입니다.")
@RequestMapping("/articles")
@Slf4j
public class ArticleController {

    private final ArticleQueryService articleQueryService;
    private final ArticleCommandService articleCommandService;

    @PostMapping
    @Operation(method = "POST", summary = "커뮤니티 글 생성 API")
    public CustomResponse<?> createArticle(@RequestBody ArticleRequestDTO.CreateArticleDTO dto) {

        Article article = articleCommandService.createArticle(dto);
        return CustomResponse.onSuccess(GeneralSuccessCode.CREATED,
                ArticleResponseDTO.CreatedArticleResponseDTO.from(article));
    }

    @GetMapping
    @Operation(method = "GET", summary = "커뮤니티 글 전체 조회 API")
    public CustomResponse<?> getArticlesByCursor(
            @RequestParam(required = false) Long cursorId,
            @RequestParam(defaultValue = "10") int pageSize
    ) {

        Slice<Article> articles = articleQueryService.getArticles(cursorId, pageSize);

        return CustomResponse.onSuccess(GeneralSuccessCode.OK,
                ArticleResponseDTO.ArticlePagePreviewListDTO.from(articles));
    }

    @GetMapping("/{articleId}")
    @Operation(method = "GET", summary = "커뮤니티 글 상세 조회 API")
    public CustomResponse<?> getArticleById(@PathVariable("articleId") Long articleId) {

        Article article = articleQueryService.getArticle(articleId);

        return CustomResponse.onSuccess(GeneralSuccessCode.OK,
                ArticleResponseDTO.ArticlePreviewDTO.from(article));
    }

    @PutMapping("/{articleId}")
    @Operation(method = "PUT", summary = "커뮤니티 글 수정 API")
    public CustomResponse<?> updateArticleById(
            @PathVariable Long articleId,
            @RequestBody ArticleRequestDTO.UpdateArticleDTO dto) {

        Article article = articleCommandService.updateArticle(articleId, dto); // 수정 반영

        return CustomResponse.onSuccess(GeneralSuccessCode.OK,
                ArticleResponseDTO.UpdatedArticleDTO.from(article));
    }

    @DeleteMapping("/{articleId}")
    @Operation(method = "DELETE", summary = "커뮤니티 글 삭제 API")
    public CustomResponse<?> deleteArticleById(@PathVariable("articleId") Long articleId) {

        Article article = articleCommandService.deleteArticle(articleId);

        return CustomResponse.onSuccess(GeneralSuccessCode.OK);
    }
}
