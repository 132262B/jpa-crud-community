package app.community.domain.post.repository;

import java.util.List;

public interface ContentRepositoryCustom {

    void deleteInQuery(List<Long> ids);
}
