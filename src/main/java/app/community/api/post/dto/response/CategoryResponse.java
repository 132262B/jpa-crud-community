package app.community.api.post.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CategoryResponse {

    private Long id;
    private String name;
    private List<ChildCategoryResponse> child = new ArrayList<>();

    @QueryProjection
    public CategoryResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Getter
    @Setter
    public static class ChildCategoryResponse {

        private Long id;
        private String name;
        private Long parentId;

        @QueryProjection
        public ChildCategoryResponse(Long id, String name, Long parentId) {
            this.id = id;
            this.name = name;
            this.parentId = parentId;
        }
    }

}
