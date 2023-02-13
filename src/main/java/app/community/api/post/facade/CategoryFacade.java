package app.community.api.post.facade;


import app.community.api.post.dto.request.CreateCategoryRequest;
import app.community.api.post.service.CategoryService;
import app.community.domain.post.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryFacade {

    private final CategoryService categoryService;

    @Transactional
    public Long create(CreateCategoryRequest request) {
        Category parentCategory = null;
        if (request.getParentId() != null) {
                parentCategory = categoryService.findCategory(request.getParentId());
        }

        Category category = Category.createCategory(request.getName(), parentCategory);
        return categoryService.create(category).getId();
    }
}
