package app.community.api.post.facade;

import app.community.api.member.service.MemberService;
import app.community.api.post.dto.request.WriteCommentRequest;
import app.community.api.post.service.CommentService;
import app.community.api.post.service.ContentService;
import app.community.domain.member.Member;
import app.community.domain.post.Category;
import app.community.domain.post.Comment;
import app.community.domain.post.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentFacade {

    private final CommentService commentService;
    private final MemberService memberService;
    private final ContentService contentService;

    @Transactional
    public Long write(WriteCommentRequest request, Long memberId) {
        Comment parentComment = null;
        if (request.getCommentId() != null)
            parentComment = commentService.findComment(request.getCommentId());

        Content content = contentService.findContent(request.getContentId());
        Member member = memberService.findMember(memberId);

        return commentService.create(request.getContent(), content, parentComment, member).getId();

    }
}
