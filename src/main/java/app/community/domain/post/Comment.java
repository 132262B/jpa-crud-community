package app.community.domain.post;

import app.community.domain.member.Member;
import app.community.global.jpa.auditing.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * 댓글 엔티티
 * @author igor
 */
@Builder
@AllArgsConstructor
@Getter
@Entity
@DynamicInsert
@Where(clause = "status = 'Y'")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private Content content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(length = 1000, nullable = false)
    private String commentContent;

    @Column(length = 1, nullable = false)
    @ColumnDefault("'Y'")
    private String status;

}
