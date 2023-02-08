package app.community.api.member.service;

import app.community.domain.member.Member;
import app.community.global.model.dto.DefaultResultResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    private final String CREATE_ACCOUNT_MEMBER_EMAIL = "member1@gmail.com";
    private final String CREATE_ACCOUNT_MEMBER_PASSWORD = "1234";
    private final String CREATE_ACCOUNT_MEMBER_USERNAME = "회원1";

    @Value("#{message['message.member.success.account']}")
    private String MESSAGE_SUCCESS_ACCOUNT;

    @Test
    @DisplayName("회원가입_테스트_성공")
    void createAccountSuccessTest() {

        // when
        DefaultResultResponse defaultResultResponse = memberService.save(CREATE_ACCOUNT_MEMBER_EMAIL, CREATE_ACCOUNT_MEMBER_PASSWORD, CREATE_ACCOUNT_MEMBER_USERNAME);

        // then
        assertThat(defaultResultResponse.getMessage()).isEqualTo(MESSAGE_SUCCESS_ACCOUNT);
        assertThat(defaultResultResponse.isSuccess()).isTrue();
    }

    @Test
    @DisplayName("회원가입_테스트_실패")
    void createAccountFailTest() {

        // when
        memberService.save(CREATE_ACCOUNT_MEMBER_EMAIL, CREATE_ACCOUNT_MEMBER_PASSWORD, CREATE_ACCOUNT_MEMBER_USERNAME);

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            memberService.save(CREATE_ACCOUNT_MEMBER_EMAIL, CREATE_ACCOUNT_MEMBER_PASSWORD, CREATE_ACCOUNT_MEMBER_USERNAME);
        }, "에러가 발생하지 않음");
    }

}