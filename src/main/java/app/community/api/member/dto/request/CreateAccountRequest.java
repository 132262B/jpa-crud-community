package app.community.api.member.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateAccountRequest {

    @NotBlank
    @Email(message = "이메일 양식을 지켜주세요.")
    private String email;

    @NotBlank
    @Length(max = 30)
    private String password;

    @NotBlank
    @Length(max = 30)
    private String username;

}
