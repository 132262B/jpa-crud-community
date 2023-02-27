package app.community.domain.post.facade;

import app.community.domain.member.service.MemberService;
import app.community.api.post.dto.request.WriteCommentRequest;
import app.community.domain.post.service.CommentService;
import app.community.domain.post.service.ContentService;
import app.community.domain.member.entity.Member;
import app.community.domain.post.entity.Comment;
import app.community.domain.post.entity.Content;
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
