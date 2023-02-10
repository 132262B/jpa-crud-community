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

    @BeforeEach
    public void beforeEach() {
        Category parentCategory = new Category("before 메인카테고리");
        categoryRepository.save(parentCategory);
        Category childCategory = new Category("before 서브카테고리", parentCategory);
        categoryRepository.save(childCategory);

    }

    @Value("#{message['message.create.category.success']}")
    private String MESSAGE_CREATE_CATEGORY_SUCCESS;

    @DisplayName("메인_카테고리_등록_성공")
    @Test
    void createParentCategorySuccessTest() {

        // given
        CreateCategoryRequest categoryRequest = generateParentCategory();

        // when
        DefaultResultResponse defaultResultResponse = categoryService.save(categoryRequest.getName(), categoryRequest.getParentId());

        // then
        assertThat(defaultResultResponse.getMessage()).isEqualTo(MESSAGE_CREATE_CATEGORY_SUCCESS);
        assertThat(defaultResultResponse.isSuccess()).isTrue();
    }

    @DisplayName("서브_카테고리_등록_성공")
    @Test
    void createChildCategorySuccessTest() {

        // given
        Category parentCategory = categoryRepository.findAll().get(0);

        CreateCategoryRequest childCategoryRequest = generateChildCategory(parentCategory.getId());

        // when
        DefaultResultResponse defaultResultResponse = categoryService.save(childCategoryRequest.getName(), childCategoryRequest.getParentId());

        // then
        assertThat(defaultResultResponse.getMessage()).isEqualTo(MESSAGE_CREATE_CATEGORY_SUCCESS);
        assertThat(defaultResultResponse.isSuccess()).isTrue();
    }

    @DisplayName("parentId가 없는경우")
    @Test
    void createChildCategorySuccessFailTest_1() {

        // given
        CreateCategoryRequest childCategoryRequest = generateChildCategory(999L);

        // then and when
        assertThrows(NullPointerException.class, () -> {
            categoryService.save(childCategoryRequest.getName(), childCategoryRequest.getParentId());
        }, "에러가 발생하지 않음");
    }

    @DisplayName("서브 카테고리에 서브카테고리를 등록할려고 하는경우")
    @Test
    void createChildCategorySuccessFailTest_2() {

        // given
        Category parentCategory = categoryRepository.findAll().get(1);

        CreateCategoryRequest childCategoryRequest = generateChildCategory(parentCategory.getId());

        // then and when
        assertThrows(IllegalArgumentException.class, () -> {
            categoryService.save(childCategoryRequest.getName(), childCategoryRequest.getParentId());
        }, "에러가 발생하지 않음");
    }

}