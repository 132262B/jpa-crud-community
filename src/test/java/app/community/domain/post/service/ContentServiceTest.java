package app.community.domain.post.service;

import app.community.domain.member.repository.MemberRepository;
import app.community.domain.post.repository.CategoryRepository;
import app.community.domain.post.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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


}