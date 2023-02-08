package app.community.api.post.controller;


import app.community.api.member.dto.request.CreateAccountRequest;
import app.community.api.post.dto.request.CreateCategoryRequest;
import app.community.api.post.service.CategoryService;
import app.community.global.model.ApiResponse;
import app.community.global.model.dto.DefaultResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<DefaultResultResponse>> save(@Validated @RequestBody CreateCategoryRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(categoryService.save(request.getName(), request.getParentId())));
    }
}
