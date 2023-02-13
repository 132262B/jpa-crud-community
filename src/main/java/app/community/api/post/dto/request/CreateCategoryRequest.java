package app.community.api.post.dto.request;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateCategoryRequest {

    @NotBlank
    @Length(max = 30)
    private String name;

    private Long parentId;

}
