package app.community.api.post.facade;


import app.community.api.post.dto.request.CreateCategoryRequest;
import app.community.api.post.dto.response.CategoryResponse;
import app.community.api.post.service.CategoryService;
import app.community.domain.post.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryFacade {

    private final CategoryService categoryService;

    @Transactional
    public Long create(CreateCategoryRequest request) {
        Category parentCategory = null;
        if (request.getParentId() != null)
            parentCategory = categoryService.findCategory(request.getParentId());

        return categoryService.create(request.getName(), parentCategory).getId();
    }

    public List<CategoryResponse> show(Long parentId) {
        if (parentId != null) {
            int level = categoryService.findCategory(parentId).getLevel();
            if (level == 2)
                throw new IllegalArgumentException("서브카테고리는 조회 할 수 없습니다.");
        }

        return categoryService.showAll(parentId);
    }
}
