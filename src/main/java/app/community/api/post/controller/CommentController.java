package app.community.api.post.controller;

import app.community.api.post.dto.request.WriteCommentRequest;
import app.community.domain.post.facade.CommentFacade;
import app.community.global.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentFacade commentFacade;

    @PostMapping
    public ResponseEntity<Void> write(@Validated @RequestBody WriteCommentRequest request) {
        Long memberId = SessionUtil.getMemberInfoAttribute().getId();
        commentFacade.write(request, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
