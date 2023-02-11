package app.community.api.member.controller;


import app.community.api.member.dto.MemberInfo;
import app.community.api.member.dto.request.CreateAccountRequest;
import app.community.api.member.dto.request.LoginRequest;
import app.community.api.member.service.MemberService;
import app.community.global.model.ApiResponse;
import app.community.global.model.dto.DefaultResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/member")
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<ApiResponse<DefaultResultResponse>> save(@Validated @RequestBody CreateAccountRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(memberService.save(request)));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<MemberInfo>> login(@Validated @RequestBody LoginRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(memberService.login(request)));
    }

    @GetMapping("/logout")
    public ResponseEntity<ApiResponse<DefaultResultResponse>> logout() {
        return ResponseEntity.ok(new ApiResponse<>(memberService.logout()));
    }


}
