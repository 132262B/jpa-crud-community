package app.community.api.member.dto.response;

import app.community.domain.member.Member;
import app.community.global.enumerated.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ShowMemberResponse {

    private String email;
    private String username;
    private Role role;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public ShowMemberResponse(Member member) {
        this.email = member.getEmail();
        this.username = member.getUsername();
        this.role = member.getRole();
        this.createDate = member.getCreatedAt();
        this.updateDate = member.getUpdatedAt();
    }
}
