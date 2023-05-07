package com.kakaoCoffee.order.controller;

import com.kakaoCoffee.common.dto.ApiResponse;
import com.kakaoCoffee.order.dto.OrderRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "커피 주문 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    @PostMapping
    @Operation(
            summary = "커피를 주문하는 API",
            description = "사용자 식별값, 커피 id를 받아 커피를 주문하는 API"
    )
    public ApiResponse<String> orderCoffee(OrderRequestDto orderRequestDto) {
        // TODO: Apply service and change return value null.
        return ApiResponse.successOf(HttpStatus.OK, null);
    }

}
