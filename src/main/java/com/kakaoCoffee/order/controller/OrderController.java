package com.kakaoCoffee.order.controller;

import com.kakaoCoffee.common.entity.Order;
import com.kakaoCoffee.order.dto.OrderRequestDto;
import com.kakaoCoffee.order.dto.OrderResponseDto;
import com.kakaoCoffee.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "커피 주문 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @Operation(
            summary = "커피를 주문하는 API",
            description = "사용자 식별값, 커피 id를 받아 커피를 주문하는 API"
    )
    public ResponseEntity<OrderResponseDto> orderBeverage(OrderRequestDto orderRequestDto) {
        return ResponseEntity.ok(orderService.orderBeverage(orderRequestDto));
    }

}
