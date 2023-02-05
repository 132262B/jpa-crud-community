package app.community.domain.post;

import app.community.global.jpa.auditing.BaseTimeAndByEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 카테고리 엔티티
 * @author igor
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseTimeAndByEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ColumnDefault("'Y'")
    private String status;

    @OneToMany(mappedBy = "category")
    private List<Content> content = new ArrayList<>();

}