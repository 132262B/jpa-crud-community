package app.community.api.post.repository;

import java.util.List;

public interface CommentRepositoryCustom {

    void deleteInQuery(List<Long> ids);
}
