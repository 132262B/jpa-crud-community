package app.community.api.post.repository;

import app.community.api.post.dto.response.CategoryResponse;
import app.community.api.post.dto.response.QCategoryResponse;
import app.community.api.post.dto.response.QCategoryResponse_ChildCategoryResponse;
import app.community.domain.post.QComment;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static app.community.domain.post.QCategory.category;
import static app.community.domain.post.QComment.*;

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
