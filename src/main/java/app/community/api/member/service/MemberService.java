package app.community.api.member.service;

import app.community.api.member.repository.MemberRepository;
import app.community.domain.member.Member;
import app.community.global.model.dto.DefaultResultResponse;
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

    @Transactional
    public DefaultResultResponse save(String email, String password, String username) {
        Member member = new Member(email, password, username);

        try {
            memberRepository.save(member);
        } catch (Exception e) {
            throw new IllegalArgumentException("이미 존재하는 이메일이 존재합니다.");
        }

        return new DefaultResultResponse(MESSAGE_SUCCESS_ACCOUNT, true);
    }
}
