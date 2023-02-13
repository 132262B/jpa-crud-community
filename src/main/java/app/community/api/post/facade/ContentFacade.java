package app.community.api.post.facade;


import app.community.api.member.service.MemberService;
import app.community.api.post.dto.request.WriteContentRequest;
import app.community.api.post.service.CategoryService;
import app.community.api.post.service.ContentService;
import app.community.domain.member.Member;
import app.community.domain.post.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ContentFacade {

    private final MemberService memberService;
    private final CategoryService categoryService;
    private final ContentService contentService;

    @Transactional
    public Long write(WriteContentRequest request, Long memberId) {
        Category category = categoryService.findCategory(request.getCategoryId());
        Member member = memberService.findMember(memberId);
        return contentService.write(member, category, request).getId();
    }
}
