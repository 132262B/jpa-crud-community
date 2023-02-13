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

    @Value("#{message['message.member.logout']}")
    private String MESSAGE_LOGOUT;

    @Transactional
    public Long create(CreateAccountRequest request) {
        Member member = Member.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .username(request.getUsername())
                .role(Role.USER)
                .build();

        try {
            return memberRepository.save(member).getId();
        } catch (Exception e) {
            throw new IllegalArgumentException("이미 존재하는 이메일이 존재합니다.");
        }
    }

    public MemberInfo login(LoginRequest request) {
        Member member = memberRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());
        return new MemberInfo(member);
    }

    public void logout() {
        SessionUtil.invalidate();
    }

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new NullPointerException("해당 사용자는 존재하지 않습니다."));
    }
}
