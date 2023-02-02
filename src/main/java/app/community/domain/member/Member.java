package app.community.domain.member;

import app.community.domain.post.Content;
import app.community.global.enumerated.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 사용자 엔티티
 * @author igor
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(length = 50)
    private String email;

    private String password;

    private String username;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String status;

    @OneToMany(mappedBy = "content")
    private List<Content> contents = new ArrayList<>();



}
