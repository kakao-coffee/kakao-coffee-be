package com.kakaoCoffee.coffee.controller;

import com.kakaoCoffee.coffee.dto.CoffeeInfoResponseDto;
import com.kakaoCoffee.coffee.dto.CoffeeSaleResponseDto;
import com.kakaoCoffee.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Tag(name = "커피 메뉴 조회 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coffees")
public class CoffeeController {

    @GetMapping("/{coffeeId}")
    @Operation(
            summary = "커피의 정보를 조회하는 API",
            description = "커피의 unique id, 커피의 이름, 커피의 가격을 조회"
    )
    public ApiResponse<CoffeeInfoResponseDto> getCoffee(@PathVariable Long coffeeId) {
        // TODO: Apply service and change return value null.
        return ApiResponse.successOf(HttpStatus.OK, null);
    }

    @GetMapping("/all")
    @Operation(
            summary = "모든 커피의 정보를 조회하는 API",
            description = "모든 커피의 unique id, 커피의 이름, 커피의 가격을 조회"
    )
    public ApiResponse<ArrayList<CoffeeInfoResponseDto>> getCoffees() {
        // TODO: Apply service and change return value null.
        return ApiResponse.successOf(HttpStatus.OK, null);
    }

    @GetMapping("/popular-coffees")
    @Operation(
            summary = "최근 7일간 인기 있는 커피 메뉴 3개를 조회하는 API",
            description = "커피 id, 커피 이름, 커피 판매 횟수 3개를 반환"
    )
    public ApiResponse<ArrayList<CoffeeSaleResponseDto>> getPopularCoffees() {
        // TODO: Apply service and change return value null.
        return ApiResponse.successOf(HttpStatus.OK, null);
    }

}
