package app.community.api.member.service;

import app.community.api.member.dto.MemberInfo;
import app.community.api.member.dto.request.CreateAccountRequest;
import app.community.api.member.dto.request.LoginRequest;
import app.community.api.member.repository.MemberRepository;
import app.community.domain.member.Member;
import app.community.global.enumerated.Role;
import app.community.global.model.dto.DefaultResultResponse;
import app.community.global.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Value("#{message['message.member.success.account']}")
    private String MESSAGE_SUCCESS_ACCOUNT;

    @Value("#{message['message.member.logout']}")
    private String MESSAGE_LOGOUT;

    @Transactional
    public DefaultResultResponse save(CreateAccountRequest request) {
        Member member = Member.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .username(request.getUsername())
                .role(Role.USER)
                .build();

        try {
            memberRepository.save(member);
        } catch (Exception e) {
            throw new IllegalArgumentException("이미 존재하는 이메일이 존재합니다.");
        }

        return new DefaultResultResponse(MESSAGE_SUCCESS_ACCOUNT, true);
    }

    public MemberInfo login(LoginRequest request) {
        Member member = memberRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());
        MemberInfo memberInfo = new MemberInfo(member);

        SessionUtil.setMemberInfoAttribute(memberInfo);

        return memberInfo;
    }

    public DefaultResultResponse logout() {
        SessionUtil.invalidate();
        return new DefaultResultResponse(MESSAGE_LOGOUT, true);
    }
}
