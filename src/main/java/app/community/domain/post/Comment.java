package app.community.domain.post;

import app.community.domain.member.Member;
import app.community.global.jpa.auditing.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 댓글 엔티티
 *
 * @author igor
 */
@Builder
@AllArgsConstructor
@Getter
@Entity
@DynamicInsert
@Where(clause = "status = 'Y'")
@SQLDelete(sql = "UPDATE comment SET status = 'N' WHERE comment_id = ?")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private Content ownerContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(length = 1000, nullable = false)
    private String content;

    @Column(length = 1, nullable = false)
    @ColumnDefault("'Y'")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent")
    private Comment parent;

    @Builder.Default
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Comment> child = new ArrayList<>();

    @Column(nullable = false)
    private Integer level;

    public static Comment createComment(String commentContent, Content content, Comment parentComment, Member member) {
        int level = 1;
        if (Objects.nonNull(parentComment)) {
            if (parentComment.getLevel() != 1)
                throw new IllegalArgumentException("답글에 답글을 등록할 수 없습니다.");
            level = 2;
        }

        return Comment.builder()
                .content(commentContent)
                .parent(parentComment)
                .ownerContent(content)
                .member(member)
                .level(level)
                .build();
    }
}
