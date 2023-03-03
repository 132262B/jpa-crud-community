package app.community.api.member.controller;


import app.community.api.member.dto.MemberInfo;
import app.community.api.member.dto.request.CreateAccountRequest;
import app.community.api.member.dto.request.LoginRequest;
import app.community.api.member.dto.request.ModifyInfoRequest;
import app.community.api.member.dto.response.ShowMemberResponse;
import app.community.domain.member.facade.MemberFacade;
import app.community.domain.member.service.MemberService;
import app.community.global.model.ApiResponse;
import app.community.global.utils.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static app.community.api.member.controller.MemberController.MEMBER_MAPPING;


@RequestMapping(MEMBER_MAPPING)
@RequiredArgsConstructor
@RestController
public class MemberController {

    public final static String MEMBER_MAPPING = "/api/member";

    private final MemberService memberService;

    private final MemberFacade memberFacade;

    @PostMapping
    public ResponseEntity<Void> create(@Validated @RequestBody CreateAccountRequest request) {
        memberFacade.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<ShowMemberResponse>> show() {
        Long memberId = SessionUtil.getMemberInfoAttribute().getId();
        return ResponseEntity.ok(new ApiResponse<>(memberFacade.show(memberId)));
    }

    @PutMapping
    public ResponseEntity<Void> modify(@Validated @RequestBody ModifyInfoRequest request) {
        Long memberId = SessionUtil.getMemberInfoAttribute().getId();
        memberFacade.modify(request, memberId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete() {
        Long memberId = SessionUtil.getMemberInfoAttribute().getId();
        memberFacade.delete(memberId);
        memberService.logout();
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<MemberInfo>> login(@Validated @RequestBody LoginRequest request) {
        MemberInfo memberInfo = memberFacade.login(request);
        SessionUtil.setMemberInfoAttribute(memberInfo);
        return ResponseEntity.ok(new ApiResponse<>(memberInfo));
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout() {
        memberService.logout();
        return ResponseEntity.noContent().build();
    }


}
