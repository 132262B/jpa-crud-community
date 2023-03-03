package app.community.global.test;

import app.community.domain.member.entity.Member;
import app.community.domain.post.entity.Content;
import app.community.global.enumerated.Role;
import app.community.global.utils.SHA256Util;

public class TestContentBuilder {

    private Member member;

    private final String content = "게시물";

    public TestContentBuilder() {
    }

    public Content build() {
        return Content.builder()
                .member(member)
                .content(content)
                .build();

    }
}
