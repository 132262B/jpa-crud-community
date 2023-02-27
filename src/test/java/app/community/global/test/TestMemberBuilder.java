package app.community.global.test;

import app.community.domain.member.entity.Member;
import app.community.global.enumerated.Role;
import app.community.global.utils.SHA256Util;

public class TestMemberBuilder {

    private String email;
    private final String password = SHA256Util.encrypt("1234");
    private final String username = "테스트유저";
    private final Role role = Role.USER;

    public TestMemberBuilder(String email) {
        this.email = email;
    }

    public Member build() {
        return Member.builder()
                .email(email)
                .password(password)
                .username(username)
                .role(role)
                .build();
    }
}
