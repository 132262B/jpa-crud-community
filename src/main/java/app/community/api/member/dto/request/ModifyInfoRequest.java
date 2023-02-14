package app.community.api.member.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ModifyInfoRequest {

    @NotBlank(message = "사용자이름은 비어있을 수 없습니다.")
    private String username;

}
