package app.community.domain.post.repository.impl;

import app.community.domain.post.repository.CommentRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static app.community.domain.post.entity.QComment.comment;


@Repository
@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    @Override
    public void deleteInQuery(List<Long> ids) {
        queryFactory
                .update(comment)
                .set(comment.status, "N")
                .where(comment.id.in(ids))
                .execute();
    }
}
