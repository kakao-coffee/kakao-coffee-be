package com.kakaoCoffee.member.controller;

import com.kakaoCoffee.common.dto.ApiResponse;
import com.kakaoCoffee.pointHistory.dto.PointChargeRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

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
