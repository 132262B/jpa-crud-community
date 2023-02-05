package app.community.api.member.service;

import app.community.api.member.dto.request.CreateAccountRequest;
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
    public DefaultResultResponse save(CreateAccountRequest request) {
        Member member = new Member(request.getEmail(), request.getPassword(), request.getUsername());
        memberRepository.save(member);

        return DefaultResultResponse.builder()
                .message(MESSAGE_SUCCESS_ACCOUNT)
                .success(true)
                .build();
    }
}
