package umc.teamc.youthStepUp.article.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.teamc.youthStepUp.article.dto.ArticleRequestDTO;
import umc.teamc.youthStepUp.article.dto.ArticleResponseDTO;
import umc.teamc.youthStepUp.article.entity.Article;
import umc.teamc.youthStepUp.article.service.command.ArticleCommandService;
import umc.teamc.youthStepUp.article.service.query.ArticleQueryService;
import umc.teamc.youthStepUp.auth.annotation.MemberInfo;
import umc.teamc.youthStepUp.global.apiPayload.CustomResponse;
import umc.teamc.youthStepUp.global.success.GeneralSuccessCode;
import umc.teamc.youthStepUp.member.dto.MemberDTO.MemberDataDTO;
import umc.teamc.youthStepUp.member.entity.Member;

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
    public CustomResponse<?> createArticle(
            @Parameter(hidden = true) @MemberInfo Long id,
            @RequestBody ArticleRequestDTO.CreateArticleDTO dto) {

        Article article = articleCommandService.createArticle(id, dto);
        Long memberId = article.getMember().getId();
        String nickName = article.getMember().getNickName();

        return CustomResponse.onSuccess(GeneralSuccessCode.CREATED,
                ArticleResponseDTO.CreatedArticleResponseDTO.from(article, memberId, nickName));
    }

    @GetMapping
    @Operation(method = "GET", summary = "커뮤니티 글 전체 조회 API")
    public CustomResponse<?> getArticlesByCursor(
            @RequestParam(name = "cursorId", required = false) Long cursorId,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize
    ) {

        Slice<Article> articles = articleQueryService.getArticles(cursorId, pageSize);

        return CustomResponse.onSuccess(GeneralSuccessCode.OK,
                ArticleResponseDTO.ArticlePagePreviewListDTO.from(articles));
    }

    @GetMapping("/{articleId}")
    @Operation(method = "GET", summary = "커뮤니티 글 상세 조회 API")
    public CustomResponse<?> getArticleById(
            @PathVariable("articleId") Long articleId) {

        Article article = articleQueryService.getArticle(articleId);

        Member member = article.getMember();
        MemberDataDTO dto = new MemberDataDTO(member.getNickName(), member.getId());

        return CustomResponse.onSuccess(GeneralSuccessCode.OK,
                ArticleResponseDTO.ArticlePreviewDTO.from(article, dto));
    }

    @PutMapping("/{articleId}")
    @Operation(method = "PUT", summary = "커뮤니티 글 수정 API")
    public CustomResponse<?> updateArticleById(
            @Parameter(hidden = true) @MemberInfo Long id,
            @PathVariable Long articleId,
            @RequestBody ArticleRequestDTO.UpdateArticleDTO dto) {

        Article article = articleCommandService.updateArticle(id, articleId, dto); // 수정 반영

        return CustomResponse.onSuccess(GeneralSuccessCode.OK,
                ArticleResponseDTO.UpdatedArticleDTO.from(article));
    }

    @DeleteMapping("/{articleId}")
    @Operation(method = "DELETE", summary = "커뮤니티 글 삭제 API")
    public CustomResponse<?> deleteArticleById(
            @Parameter(hidden = true) @MemberInfo Long id,
            @PathVariable("articleId") Long articleId) {

        Article article = articleCommandService.deleteArticle(id, articleId);

        return CustomResponse.onSuccess(GeneralSuccessCode.OK);
    }
}
