package app.community.api.member.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

@Getter
@Setter
public class CreateAccountRequest {

    @Email
    private String email;

    @Length(max = 30)
    private String password;

    @Length(max = 30)
    private String username;

}
