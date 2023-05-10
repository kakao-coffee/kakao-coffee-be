package com.kakaoCoffee.member.controller;

import com.kakaoCoffee.member.dto.LoginRequestDto;
import com.kakaoCoffee.member.dto.MemberResponseDto;
import com.kakaoCoffee.member.dto.SignupRequestDto;
import com.kakaoCoffee.member.service.MemberService;
import com.kakaoCoffee.pointHistory.dto.PointChargeRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Tag(name = "회원 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    @Operation(summary = "회원 가입", description = "회원 가입하는 API")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.signup(signupRequestDto));
    }

    @PostMapping("/login")
    @Operation(summary = "로그인")
    public ResponseEntity<MemberResponseDto> login(
            @RequestBody LoginRequestDto loginRequestDto,
            @Parameter(hidden = true) HttpServletResponse response
    ) {
        return ResponseEntity.ok(memberService.login(loginRequestDto, response));
    }

    @GetMapping("/member-id/duplicate")
    @Operation(summary = "아이디 중복체크", description = "아이디 중복이면 true, 중복이 아니면 false")
    public ResponseEntity<Boolean> checkMemberId(@RequestParam String memberId) {
        return ResponseEntity.ok(memberService.checkMemberIdDuplication(memberId));
    }

    @PatchMapping("/point")
    @Operation(
            summary = "포인트를 충전하는 API",
            description = "사용자 식별값, 충전금액을 받아 포인트를 충전"
    )
    public ResponseEntity<String> chargePoint(PointChargeRequestDto pointChargeRequestDto) {
        // TODO: Apply service and change return value null.
        return ResponseEntity.ok(null);
    }
}
