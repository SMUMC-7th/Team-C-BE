package umc.teamc.youthStepUp.article.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.teamc.youthStepUp.article.dto.ArticleRequestDTO;
import umc.teamc.youthStepUp.article.entity.Article;
import umc.teamc.youthStepUp.article.exception.ArticleErrorCode;
import umc.teamc.youthStepUp.article.exception.ArticleErrorException;
import umc.teamc.youthStepUp.article.repository.ArticleRepository;
import umc.teamc.youthStepUp.member.entity.Member;
import umc.teamc.youthStepUp.member.error.MemberErrorCode;
import umc.teamc.youthStepUp.member.error.exception.MemberCustomException;
import umc.teamc.youthStepUp.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleCommandServiceImpl implements ArticleCommandService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    @Override
    public Article createArticle(Long memberId, ArticleRequestDTO.CreateArticleDTO dto) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberCustomException(MemberErrorCode.MEMBER_NOT_FOUND)
        );
        return articleRepository.save(dto.toEntity(member));
    }

    @Override
    public Article updateArticle(Long memberId, Long articleId, ArticleRequestDTO.UpdateArticleDTO dto) {

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleErrorException(ArticleErrorCode.NOT_FOUND));


        // 작성자 검증(memberId 이용)
        if (!article.getMember().getId().equals(memberId)) {
            throw new ArticleErrorException(ArticleErrorCode.FORBIDDEN);
        }

        article.updateArticle(dto.getTitle(), dto.getContent());
        return article;
    }

    @Override
    public Article deleteArticle(Long memberId, Long articleId) {

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleErrorException(ArticleErrorCode.NOT_FOUND));

        if (!article.getMember().getId().equals(memberId)) {
            throw new ArticleErrorException(ArticleErrorCode.FORBIDDEN);
        }

        articleRepository.deleteById(articleId);


        return article;
    }
}
