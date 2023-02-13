package app.community.api.post.service;

import app.community.api.post.dto.request.CreateCategoryRequest;
import app.community.api.post.repository.CategoryRepository;
import app.community.domain.post.Category;
import app.community.global.model.dto.DefaultResultResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@DisplayName("카테고리 테스트")
class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    // 메인카테고리
    private CreateCategoryRequest generateParentCategory() {
        CreateCategoryRequest createCategoryRequest = new CreateCategoryRequest();
        createCategoryRequest.setName("메인카테고리");
        createCategoryRequest.setParentId(null);
        return createCategoryRequest;
    }

    // 서브 카테고리
    private CreateCategoryRequest generateChildCategory(Long parentId) {
        CreateCategoryRequest createCategoryRequest = new CreateCategoryRequest();
        createCategoryRequest.setName("서브카테고리");
        createCategoryRequest.setParentId(parentId);
        return createCategoryRequest;
    }

}