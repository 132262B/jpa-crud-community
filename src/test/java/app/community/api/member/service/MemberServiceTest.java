package app.community.api.member.service;

import app.community.domain.member.Member;
import app.community.global.test.TestMemberBuilder;
import app.community.global.utils.SHA256Util;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@DisplayName("맴버 테스트")
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private EntityManager em;

    private final String TEST_EMAIL = "test1@testmail.com";

    @DisplayName("회원가입_성공")
    @Test
    public void createAccountSuccessTest() {
        // given
        Member member = new TestMemberBuilder(TEST_EMAIL).build();
        // when
        Long id = memberService.create(member).getId();
        // then
        assertThat(id).isPositive();
    }

    @DisplayName("회원가입_실패_이메일중복")
    @Test
    public void createAccountFailTest() {
        // given
        Member member1 = new TestMemberBuilder(TEST_EMAIL).build();
        memberService.create(member1);

        Member member2 = new TestMemberBuilder(TEST_EMAIL).build();

        // when
        // then
        assertThrows(IllegalArgumentException.class,
                () -> memberService.create(member2), "이메일이 중복되지 않음");
    }

    @DisplayName("로그인_성공")
    @Test
    void loginSuccessTest() {
        // given
        Member createMember = new TestMemberBuilder(TEST_EMAIL).build();
        memberService.create(createMember);

        // when
        Member loginMember = memberService.login(TEST_EMAIL, SHA256Util.encrypt("1234"));

        // then
        assertAll(
                () -> assertThat(createMember.getEmail()).isEqualTo(loginMember.getEmail()),
                () -> assertThat(createMember.getUsername()).isEqualTo(loginMember.getUsername()),
                () -> assertThat(createMember.getRole()).isEqualTo(loginMember.getRole())
        );
    }

    @DisplayName("로그인_실패")
    @Test
    void loginFailTest() {
        // given
        Member createMember = new TestMemberBuilder(TEST_EMAIL).build();
        memberService.create(createMember);

        // when
        // then
        assertThrows(NullPointerException.class,
                () -> memberService.login(TEST_EMAIL, SHA256Util.encrypt("12345")), "존재하지 않는 회원");
    }

    @DisplayName("회원_조회_성공")
    @Test
    void findMemberSuccessTest() {
        // given
        Member createMember = new TestMemberBuilder(TEST_EMAIL).build();
        Long id = memberService.create(createMember).getId();

        // when
        Member selectMember = memberService.findMember(id);

        // then
        assertAll(
                () -> assertThat(createMember.getEmail()).isEqualTo(selectMember.getEmail()),
                () -> assertThat(createMember.getUsername()).isEqualTo(selectMember.getUsername()),
                () -> assertThat(createMember.getRole()).isEqualTo(selectMember.getRole())
        );
    }

    @DisplayName("회원_조회_실패")
    @Test
    void findMemberFailTest() {
        // given
        // when
        // then
        assertThrows(NullPointerException.class,
                () -> memberService.findMember(999L), "존재하지 않는 회원");
    }

    @DisplayName("회원정보_변경_성공")
    @Test
    void modifyTest() {
        // given
        Member createMember = new TestMemberBuilder(TEST_EMAIL).build();
        memberService.create(createMember);

        String modifyName = "변경된테스트유저";

        // when
        Member loginMember = memberService.modify(createMember, modifyName);

        // then
        assertThat(loginMember.getUsername()).isEqualTo(modifyName);
    }

    @DisplayName("회원탈퇴_성공")
    @Test
    void deleteTest() {
        // given
        Member member = new TestMemberBuilder(TEST_EMAIL).build();
        Long id = memberService.create(member).getId();

        // when
        memberService.deleteMember(id);

        // then
        assertThrows(NullPointerException.class,
                () -> memberService.findMember(id), "존재하지 않는 회원");
    }

}