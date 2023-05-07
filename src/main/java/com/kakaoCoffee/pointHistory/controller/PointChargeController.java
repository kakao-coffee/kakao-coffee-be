package com.kakaoCoffee.pointHistory.controller;

import com.kakaoCoffee.common.dto.ApiResponse;
import com.kakaoCoffee.common.security.UserDetailsImpl;
import com.kakaoCoffee.pointHistory.dto.PointChargeRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "포인트 충전 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/point-charges")
public class PointChargeController {

    @PostMapping("/charge")
    @Operation(
            summary = "포인트를 충전하는 API",
            description = "사용자 식별값, 충전금액을 받아 포인트를 충전"
    )
    public ApiResponse<String> chargePoint(PointChargeRequestDto pointChargeRequestDto) {
        // TODO: Apply service and change return value null.
        return ApiResponse.successOf(HttpStatus.OK, null);
    }

}
