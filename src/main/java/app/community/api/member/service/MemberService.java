package app.community.api.member.service;

import app.community.api.member.repository.MemberRepository;
import app.community.domain.member.Member;
import app.community.global.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member create(Member member) throws Exception {
        return memberRepository.save(member);

    }

    public Member login(String email, String password) {
        return memberRepository.findByEmailAndPassword(email, password);
    }

    public void logout() {
        SessionUtil.invalidate();
    }

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new NullPointerException("해당 사용자는 존재하지 않습니다."));
    }

    @Transactional
    public Member modify(Member member, String username) {
        member.changeUsername(username);
        return member;
    }
}
