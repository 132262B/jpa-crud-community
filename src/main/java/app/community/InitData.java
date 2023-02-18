package app.community;

import app.community.domain.post.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@RequiredArgsConstructor
public class InitData {

    private final InitMemberService initMemberService;

    @PostConstruct
    public void init() {
        initMemberService.initData();
    }

    @Component
    static class InitMemberService {
        @PersistenceContext
        private EntityManager em;

        @Transactional
        public void initData() {

            Category category1 = Category.createCategory("재미게시판", null);
            em.persist(category1);

            Category category2 = Category.createCategory("똥게시판", category1);
            Category category3 = Category.createCategory("똥똥게시판", category1);
            em.persist(category2);
            em.persist(category3);

            Category category4 = Category.createCategory("코딩게시판", null);
            em.persist(category4);

            Category category5 = Category.createCategory("자바", category4);
            Category category6 = Category.createCategory("C", category4);
            em.persist(category5);
            em.persist(category6);

        }

    }

}
