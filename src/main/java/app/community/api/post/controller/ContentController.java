package app.community.api.post.controller;

import app.community.api.post.dto.request.SaveContentRequest;
import app.community.api.post.service.ContentService;
import app.community.global.model.ApiResponse;
import app.community.global.model.dto.DefaultResultResponse;
import app.community.global.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/content")
public class ContentController {

    private final ContentService contentService;

    @PostMapping
    public ResponseEntity<ApiResponse<DefaultResultResponse>> save(
            @Validated @RequestBody SaveContentRequest request) {
        Long memberId = SessionUtil.getMemberInfoAttribute().getId();
        return ResponseEntity.ok(new ApiResponse<>(contentService.save(request, memberId)));
    }
}
