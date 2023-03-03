package app.community.domain.post.entity;

import app.community.domain.member.entity.Member;
import app.community.global.jpa.auditing.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;

/**
 * 게시물 엔티티
 * @author igor
 */
@Builder
@AllArgsConstructor
@Getter
@Entity
@DynamicInsert
@Where(clause = "status = 'Y'")
@SQLDelete(sql = "UPDATE content SET status = 'N' WHERE content_id = ?")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Content extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "content")
    private List<Comment> comment;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 2000, nullable = false)
    private String content;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int views;

    @Column(length = 1, nullable = false)
    @ColumnDefault("'Y'")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static Content writeContent(Member member, Category category, String title, String content) {
        return Content.builder()
                .member(member)
                .category(category)
                .title(title)
                .content(content)
                .build();
    }
}
