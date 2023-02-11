package app.community.api.member.service;

import app.community.api.member.dto.request.CreateAccountRequest;
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
@DisplayName("맴버 테스트")
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Value("#{message['message.member.success.account']}")
    private String MESSAGE_SUCCESS_ACCOUNT;

    private CreateAccountRequest generateCreateAccountRequest() {
        CreateAccountRequest createAccountRequest = new CreateAccountRequest();
        createAccountRequest.setEmail("member1@gmail.com");
        createAccountRequest.setPassword("1234");
        createAccountRequest.setUsername("회원1");
        return createAccountRequest;
    }

    @Test
    @DisplayName("회원가입_테스트_성공")
    void createAccountSuccessTest() {

        // given
        CreateAccountRequest request = generateCreateAccountRequest();

        // when
        DefaultResultResponse defaultResultResponse = memberService.save(request);

        // then
        assertThat(defaultResultResponse.getMessage()).isEqualTo(MESSAGE_SUCCESS_ACCOUNT);
        assertThat(defaultResultResponse.isSuccess()).isTrue();
    }

    @Test
    @DisplayName("회원가입_테스트_실패")
    void createAccountFailTest() {

        // given
        CreateAccountRequest request = generateCreateAccountRequest();
        memberService.save(request);

        // when and then
        assertThrows(IllegalArgumentException.class, () -> {
            memberService.save(request);
        }, "에러가 발생하지 않음");
    }

}