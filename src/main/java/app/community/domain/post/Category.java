package app.community.domain.post;

import app.community.global.jpa.auditing.BaseTimeAndByEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 카테고리 엔티티
 *
 * @author igor
 */
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

    private Integer level;

    public Category(String name) {
        this.name = name;
        this.level = 1;
    }

    public Category(String name, Category parentCategory) {
        if (parentCategory.getLevel() != 1)
            throw new IllegalArgumentException("서브 카테고리에 서브 카테고리를 등록할 수 없습니다.");

        this.name = name;
        this.parent = parentCategory;
        this.level = 2;
    }
}