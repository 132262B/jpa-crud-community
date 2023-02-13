package app.community.api.post.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class WriteContentRequest {

    @NotBlank(message = "게시물 제목은 필수값 입니다.")
    @Length(max = 100)
    private String title;

    @NotBlank(message = "게시물 내용은 필수값 입니다.")
    @Length(max = 2000)
    private String content;

    @NotNull
    private Long categoryId;

}
