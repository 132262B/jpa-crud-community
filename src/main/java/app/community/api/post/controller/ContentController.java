package app.community.api.post.controller;

import app.community.api.post.dto.request.WriteContentRequest;
import app.community.domain.post.facade.ContentFacade;
import app.community.global.model.ApiResponse;
import app.community.global.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static app.community.global.utils.CreateUriUtil.createUri;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/content")
public class ContentController {

    private final ContentFacade contentFacade;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> write(@Validated @RequestBody WriteContentRequest request) {
        Long memberId = SessionUtil.getMemberInfoAttribute().getId();
        Long contentId = contentFacade.write(request, memberId);
        return ResponseEntity.created(createUri(contentId)).build();
    }
}
