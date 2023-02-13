package app.community.domain.post;

import app.community.global.jpa.auditing.BaseTimeAndByEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 카테고리 엔티티
 *
 * @author igor
 */
@Builder
@AllArgsConstructor
@Getter
@Entity
@DynamicInsert
@Where(clause = "status = 'Y'")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseTimeAndByEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 1, nullable = false)
    @ColumnDefault("'Y'")
    private String status;

    @OneToMany(mappedBy = "category")
    private List<Content> content = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent")
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Category> child = new ArrayList<>();

    @Column(nullable = false)
    private Integer level;

    public static Category createCategory(String categoryName, Category parentCategory) {
        int level = 1;
        if (!Objects.isNull(parentCategory)) {
            if(parentCategory.getLevel() != 1)
                throw new IllegalArgumentException("서브 카테고리에 서브 카테고리를 등록할 수 없습니다.");
            level = 2;
        }

        return Category.builder()
                .name(categoryName)
                .level(level)
                .parent(parentCategory)
                .build();
    }
}