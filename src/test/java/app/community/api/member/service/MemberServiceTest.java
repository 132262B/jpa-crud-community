package app.community.api.member.service;

import app.community.api.member.dto.request.CreateAccountRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@DisplayName("맴버 테스트")
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    private CreateAccountRequest generateCreateAccountRequest() {
        CreateAccountRequest createAccountRequest = new CreateAccountRequest();
        createAccountRequest.setEmail("member1@gmail.com");
        createAccountRequest.setPassword("1234");
        createAccountRequest.setUsername("회원1");
        return createAccountRequest;
    }

    @Test
    @DisplayName("회원가입_가입_실패")
    void createAccountFailTest() {

        // given
        CreateAccountRequest request = generateCreateAccountRequest();
        memberService.create(request);

        // when and then
        assertThrows(IllegalArgumentException.class, () -> {
            memberService.create(request);
        }, "에러가 발생하지 않음");
    }

}