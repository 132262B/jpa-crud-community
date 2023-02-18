package app.community.api.post.repository;

import java.util.List;

public interface ContentRepositoryCustom {

    void deleteInQuery(List<Long> ids);
}
