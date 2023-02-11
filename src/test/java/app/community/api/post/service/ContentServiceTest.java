package app.community.api.post.service;

import app.community.api.member.dto.MemberInfo;
import app.community.api.member.repository.MemberRepository;
import app.community.api.post.dto.request.SaveContentRequest;
import app.community.api.post.repository.CategoryRepository;
import app.community.domain.member.Member;
import app.community.domain.post.Category;
import app.community.global.enumerated.Role;
import app.community.global.model.dto.DefaultResultResponse;
import app.community.global.utils.SessionUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class ContentServiceTest {

    @Autowired
    private ContentService contentService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MemberRepository memberRepository;


    private Long categoryId;
    private Long memberId;

    @BeforeEach
    public void beforeEach() {
        memberId = memberRepository.save(Member.builder()
                .email("member1@gmail.com")
                .password("1234")
                .username("회원1")
                .role(Role.USER)
                .build()).getId();

        Category category = categoryRepository.save(Category.builder()
                .name("메인카테고리")
                .build());

        categoryId = categoryRepository.save(Category.builder()
                .name("서브카테고리")
                .parent(category)
                .build()).getId();
    }

    @Value("#{message['message.content.save']}")
    private String MESSAGE_CONTENT_SAVE;

    @Test
    void saveContentSuccessTest() {
        // given
        SaveContentRequest request = new SaveContentRequest();
        request.setTitle("이것은제모오오옥압니다.");
        request.setContent("이것은내에에에용압니다.");
        request.setCategoryId(categoryId);

        // when
        DefaultResultResponse response = contentService.save(request, memberId);

        // then
        assertThat(response.getMessage()).isEqualTo(MESSAGE_CONTENT_SAVE);
        assertThat(response.isSuccess()).isTrue();

    }

}