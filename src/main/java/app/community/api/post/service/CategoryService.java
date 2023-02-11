package app.community.api.post.service;

import app.community.api.post.dto.request.CreateCategoryRequest;
import app.community.api.post.repository.CategoryRepository;
import app.community.domain.post.Category;
import app.community.global.model.dto.DefaultResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Null;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Value("#{message['message.create.category.success']}")
    private String MESSAGE_CREATE_CATEGORY_SUCCESS;

    @Transactional
    public DefaultResultResponse save(CreateCategoryRequest request) {
        if (request.getParentId() != null) {
            Category parentCategory = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() -> new NullPointerException("해당 카테고리는 존재하지 않습니다."));

            Category category = new Category(request.getName(), parentCategory);
            categoryRepository.save(category);
        } else {
            Category category = new Category(request.getName());
            categoryRepository.save(category);
        }

        return new DefaultResultResponse(MESSAGE_CREATE_CATEGORY_SUCCESS, true);
    }
}
