package app.community.api.post.service;

import app.community.api.member.repository.MemberRepository;
import app.community.api.post.repository.CategoryRepository;
import app.community.domain.member.Member;
import app.community.domain.post.Category;
import app.community.global.enumerated.Role;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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

}