package app.community.api.post.service;

import app.community.api.post.repository.CategoryRepository;
import app.community.domain.post.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    public Category findCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 카테고리는 존재하지 않습니다."));
    }
}
