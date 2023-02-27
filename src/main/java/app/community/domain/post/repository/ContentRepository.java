package app.community.domain.post.repository;

import app.community.domain.post.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Long>, ContentRepositoryCustom {
}