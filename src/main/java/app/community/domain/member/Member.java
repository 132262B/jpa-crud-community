package app.community.domain.member;

import app.community.domain.post.Content;
import app.community.global.enumerated.Role;
import app.community.global.jpa.auditing.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
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
@Where(clause = "status = 'Y'")
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true, length = 50)
    private String email;

    private String password;

    private String username;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ColumnDefault("'Y'")
    private String status;

    @OneToMany(mappedBy = "content")
    private List<Content> contents = new ArrayList<>();


    public Member(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.role = Role.USER;
    }

}
