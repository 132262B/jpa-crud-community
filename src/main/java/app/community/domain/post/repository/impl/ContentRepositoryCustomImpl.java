package app.community.domain.post.repository.impl;

import app.community.domain.post.repository.ContentRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static app.community.domain.post.QContent.content1;

@Repository
@RequiredArgsConstructor
public class ContentRepositoryCustomImpl implements ContentRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    @Override
    public void deleteInQuery(List<Long> ids) {
        queryFactory
                .update(content1)
                .set(content1.status, "N")
                .where(content1.id.in(ids))
                .execute();
    }
}
