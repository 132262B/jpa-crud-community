package app.community.api.member.dto;

import app.community.domain.member.Member;
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

    public static MemberInfo createMemberInfo(Member member) {
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setId(member.getId());
        memberInfo.setEmail(member.getEmail());
        memberInfo.setUsername(member.getUsername());
        memberInfo.setRole(member.getRole());
        return memberInfo;
    }
}
