package app.community.api.post.service;


import app.community.api.member.repository.MemberRepository;
import app.community.api.post.dto.request.SaveContentRequest;
import app.community.api.post.repository.CategoryRepository;
import app.community.api.post.repository.ContentRepository;
import app.community.domain.member.Member;
import app.community.domain.post.Category;
import app.community.domain.post.Content;
import app.community.global.model.dto.DefaultResultResponse;
import app.community.global.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;

    @Value("#{message['message.content.save']}")
    private String MESSAGE_CONTENT_SAVE;

    @Transactional
    public DefaultResultResponse save(SaveContentRequest request, Long memberId) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new NullPointerException("해당 카테고리는 존재하지 않습니다."));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NullPointerException("해당 사용자는 존재하지 않습니다."));

        Content content = Content.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .category(category)
                .member(member)
                .build();

        contentRepository.save(content);

        return new DefaultResultResponse(MESSAGE_CONTENT_SAVE, true);
    }
}
