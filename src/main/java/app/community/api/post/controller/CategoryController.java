package app.community.api.post.controller;


import app.community.api.post.dto.request.CreateCategoryRequest;
import app.community.api.post.facade.CategoryFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/category")
@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryFacade categoryFacade;

    @PostMapping
    public ResponseEntity<Void> save(@Validated @RequestBody CreateCategoryRequest request) {
        categoryFacade.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
