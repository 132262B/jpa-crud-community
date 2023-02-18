package app.community.api.post.controller;

import app.community.api.post.dto.request.CreateCategoryRequest;
import app.community.api.post.dto.response.CategoryResponse;
import app.community.api.post.facade.CategoryFacade;
import app.community.global.model.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> showAll() {
        return ResponseEntity.ok(new ApiResponse<>(categoryFacade.show(null)));
    }

    @GetMapping("/{parentId}")
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> showId(@PathVariable(required = false) Long parentId) {
        return ResponseEntity.ok(new ApiResponse<>(categoryFacade.show(parentId)));
    }


}
