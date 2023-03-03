package app.community.domain.post.service;


import app.community.api.post.dto.request.WriteContentRequest;
import app.community.domain.post.repository.ContentRepository;
import app.community.domain.member.entity.Member;
import app.community.domain.post.entity.Category;
import app.community.domain.post.entity.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;

    @Transactional
    public Content write(Member member, Category category, WriteContentRequest request) {
        Content content = Content.writeContent(member, category, request.getTitle(), request.getContent());
        return contentRepository.save(content);
    }

    public Content findContent(Long id) {
        return contentRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("등록된 게시물이 존재하지 않습니다."));
    }

    @Transactional
    public void deleteAllContent(List<Long> ids) {
        if(ids.size() != 0)
            contentRepository.deleteInQuery(ids);
    }
}
