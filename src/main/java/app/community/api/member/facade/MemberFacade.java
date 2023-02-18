package app.community.api.member.facade;

import app.community.api.member.dto.MemberInfo;
import app.community.api.member.dto.request.CreateAccountRequest;
import app.community.api.member.dto.request.LoginRequest;
import app.community.api.member.dto.request.ModifyInfoRequest;
import app.community.api.member.dto.response.ShowMemberResponse;
import app.community.api.member.service.MemberService;
import app.community.api.post.service.CommentService;
import app.community.api.post.service.ContentService;
import app.community.domain.member.Member;
import app.community.domain.post.Comment;
import app.community.domain.post.Content;
import app.community.global.enumerated.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberFacade {

    private final MemberService memberService;
    private final ContentService contentService;
    private final CommentService commentService;


    @Transactional
    public Long create(CreateAccountRequest request) {
        Member member = Member.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .username(request.getUsername())
                .role(Role.USER)
                .build();

        return memberService.create(member).getId();
    }

    @Transactional
    public Long modify(ModifyInfoRequest request, Long memberId) {
        Member member = memberService.findMember(memberId);
        return memberService.modify(member, request.getUsername()).getId();
    }

    public MemberInfo login(LoginRequest request) {
        Member member = memberService.login(request.getEmail(), request.getPassword());
        return new MemberInfo(member);
    }

    public ShowMemberResponse show(Long memberId) {
        Member member = memberService.findMember(memberId);
        return new ShowMemberResponse(member);
    }

    @Transactional
    public void delete(Long memberId) {
        Member member = memberService.findMember(memberId);

        List<Long> commentIds = member.getComments().stream()
                .map(Comment::getId)
                .collect(Collectors.toList());
        commentService.deleteAllComment(commentIds);

        List<Long> contentIds = member.getContents().stream()
                .map(Content::getId)
                .collect(Collectors.toList());
        contentService.deleteAllContent(contentIds);

        memberService.deleteMember(member.getId());

    }
}
