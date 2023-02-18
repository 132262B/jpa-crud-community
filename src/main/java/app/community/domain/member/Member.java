package app.community.domain.member;

import app.community.domain.post.Content;
import app.community.global.enumerated.Role;
import app.community.global.jpa.auditing.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 사용자 엔티티
 *
 * @author igor
 */
@Getter
@Entity
@Builder
@AllArgsConstructor
@DynamicInsert
@Where(clause = "status = 'Y'")
@SQLDelete(sql = "UPDATE member SET status = 'N' WHERE member_id = ?")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true, length = 50, nullable = false)
    private String email;

    @Column(length = 64, nullable = false)
    private String password;

    @Column(length = 30, nullable = false)
    private String username;

    @Column(length = 5, nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @ColumnDefault("'Y'")
    @Column(length = 1, nullable = false)
    private String status;

    @Builder.Default
    @OneToMany(mappedBy = "content")
    private List<Content> contents = new ArrayList<>();

    public void changeUsername(String username) {
        this.username = username;
    }
}
