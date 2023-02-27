package app.community.api.member.dto;

import app.community.domain.member.entity.Member;
import app.community.global.enumerated.Role;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberInfo {

    private Long id;

    private String email;

    private String username;

    private Role role;

    public MemberInfo (Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.username = member.getUsername();
        this.role = member.getRole();
    }
}
