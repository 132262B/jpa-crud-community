package app.community.api.member.facade;

import app.community.api.member.dto.MemberInfo;
import app.community.api.member.dto.request.CreateAccountRequest;
import app.community.api.member.dto.request.LoginRequest;
import app.community.api.member.repository.MemberRepository;
import app.community.api.member.service.MemberService;
import app.community.domain.member.Member;
import app.community.global.enumerated.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberFacade {

    private final MemberService memberService;


    @Transactional
    public Long create(CreateAccountRequest request) {
        Member member = Member.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .username(request.getUsername())
                .role(Role.USER)
                .build();

        try {
            return memberService.create(member).getId();
        } catch (Exception e) {
            throw new IllegalArgumentException("이미 존재하는 이메일이 존재합니다.");
        }
    }

    public MemberInfo login(LoginRequest request) {
        Member member = memberService.login(request.getEmail(), request.getPassword());
        return MemberInfo.createMemberInfo(member);
    }
}
