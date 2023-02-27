package app.community.domain.post.service;

import app.community.api.post.dto.request.CreateCategoryRequest;
import app.community.domain.post.repository.CategoryRepository;
import app.community.domain.post.service.CategoryService;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

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

//    @BeforeEach
//    public void beforeEach() {
//        Category parentCategory = new Category("before 메인카테고리");
//        categoryRepository.save(parentCategory);
//        Category childCategory = new Category("before 서브카테고리", parentCategory);
//        categoryRepository.save(childCategory);
//
//    }


}