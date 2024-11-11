package umc.teamc.youthStepUp.reply.service.query;

import org.springframework.data.domain.Slice;
import umc.teamc.youthStepUp.reply.entity.Reply;

public interface ReplyQueryService {

    Slice<Reply> getRepliesByArticleId(Long articleId, Long cursorId, int pageSize);
}
