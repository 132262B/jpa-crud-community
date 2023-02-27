package app.community.domain.member.service;

import app.community.domain.member.repository.MemberRepository;
import app.community.domain.member.entity.Member;
import app.community.global.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member create(Member member) {
        try {
            return memberRepository.save(member);
        } catch (Exception e) {
            throw new IllegalArgumentException("이미 존재하는 이메일이 존재합니다.");
        }
    }

    public Member login(String email, String password) {
        Member member = memberRepository.findByEmailAndPassword(email, password);
        if(Objects.isNull(member))
            throw new NullPointerException("아이디 또는 비밀번호가 일치하지 않습니다.");

        return member;
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

    @Transactional
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
