package app.community.api.member.controller;

import app.community.api.member.dto.request.CreateAccountRequest;
import app.community.global.utils.SHA256Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static app.community.api.member.controller.MemberController.MEMBER_MAPPING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final String EMAIL = "member@testmail.com";
    private final String PASSWORD = SHA256Util.encrypt("1234");
    private final String USERNAME = "회원";


    @DisplayName("회원가입 성공")
    @Test
    void createMemberSuccessTest() throws Exception {

        // given
        CreateAccountRequest request = new CreateAccountRequest(EMAIL, PASSWORD, USERNAME);

        // when
        MockHttpServletResponse response = 회원가입(request);

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

//    @DisplayName("회원가입 실패")
//    @Test
//    void createMemberFailTest() throws Exception {
//
//        // given
//        CreateAccountRequest request = new CreateAccountRequest(EMAIL, PASSWORD, USERNAME);
//        회원가입(request);
//
//        // when
//        MockHttpServletResponse response = 회원가입(request);
//
//        // then
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
//    }


    private MockHttpServletResponse 회원가입(CreateAccountRequest request) throws Exception {
        return mockMvc.perform(post(MEMBER_MAPPING)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andReturn().getResponse();
    }


}