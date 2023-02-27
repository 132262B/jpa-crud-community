package app.community.domain.post.repository;

import app.community.api.post.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryRepositoryCustom {

    List<CategoryResponse> showAll(Long parentId);
}
