package app.community.api.post.repository;

import app.community.api.post.dto.response.CategoryResponse;
import app.community.api.post.dto.response.QCategoryResponse;
import app.community.api.post.dto.response.QCategoryResponse_ChildCategoryResponse;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static app.community.domain.post.QCategory.category;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryCustomImpl implements CategoryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CategoryResponse> showAll(Long parentId) {
        List<CategoryResponse> parents = queryFactory
                .select(new QCategoryResponse(
                        category.id,
                        category.name))
                .from(category)
                .where(category.level.eq(1),
                        idEq(parentId))
                .orderBy(category.id.asc())
                .fetch();

        List<CategoryResponse.ChildCategoryResponse> child = queryFactory
                .select(new QCategoryResponse_ChildCategoryResponse(
                        category.id,
                        category.name,
                        category.parent.id
                ))
                .from(category)
                .where(category.level.eq(2),
                        parentIdEq(parentId))
                .fetch();

        parents.forEach(parentList -> parentList.setChild(
                child.stream()
                        .filter(childList -> Objects.equals(childList.getParentId(),
                                parentList.getId()))
                        .collect(Collectors.toList())));

        return parents;
    }

    private Predicate parentIdEq(Long parentId) {
        return parentId == null ? null : category.parent.id.eq(parentId);
    }

    private Predicate idEq(Long id) {
        return id == null ? null : category.id.eq(id);
    }

}
