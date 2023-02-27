package app.community.domain.post.repository;

import java.util.List;

public interface CommentRepositoryCustom {

    void deleteInQuery(List<Long> ids);
}
