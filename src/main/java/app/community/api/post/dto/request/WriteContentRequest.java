package app.community.api.post.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class WriteContentRequest {

    @NotBlank
    @Length(max = 100)
    private String title;

    @NotBlank
    @Length(max = 2000)
    private String content;

    @NotBlank
    private Long categoryId;

}
