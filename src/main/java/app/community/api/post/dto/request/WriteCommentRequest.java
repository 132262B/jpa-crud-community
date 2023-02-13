package app.community.api.post.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class WriteCommentRequest {

    @Length(max = 1000)
    @NotBlank(message = "댓글 내용을 입력해주세요.")
    private String content;

    @NotNull(message = "게시물ID는 필수값 입니다.")
    private Long contentId;

    private Long commentId;

}
