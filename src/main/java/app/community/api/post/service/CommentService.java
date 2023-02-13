package app.community.api.post.service;

import app.community.api.post.repository.CommentRepository;
import app.community.domain.member.Member;
import app.community.domain.post.Comment;
import app.community.domain.post.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment findComment(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 댓글은 존재하지 않는 댓글입니다."));
    }

    public Comment create(String commentContent, Content content, Comment parentComment, Member member) {
        Comment comment = Comment.createComment(commentContent, content, parentComment, member);
        return commentRepository.save(comment);
    }
}
