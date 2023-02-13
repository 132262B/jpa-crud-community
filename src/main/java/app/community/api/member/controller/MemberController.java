package app.community.api.member.controller;


import app.community.api.member.dto.MemberInfo;
import app.community.api.member.dto.request.CreateAccountRequest;
import app.community.api.member.dto.request.LoginRequest;
import app.community.api.member.service.MemberService;
import app.community.global.model.ApiResponse;
import app.community.global.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/member")
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Void> create(@Validated @RequestBody CreateAccountRequest request) {
        memberService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<MemberInfo>> login(@Validated @RequestBody LoginRequest request) {
        MemberInfo memberInfo = memberService.login(request);
        SessionUtil.setMemberInfoAttribute(memberInfo);
        return ResponseEntity.ok(new ApiResponse<>(memberInfo));
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout() {
        memberService.logout();
        return ResponseEntity.noContent().build();
    }


}
