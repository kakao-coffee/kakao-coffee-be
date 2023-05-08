package com.kakaoCoffee.beverage.controller;

import com.kakaoCoffee.beverage.dto.BeverageInfoResponseDto;
import com.kakaoCoffee.beverage.dto.BeverageSaleResponseDto;
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
@RequestMapping("/api/beverages")
public class BeverageController {

    @GetMapping("/{beverageId}")
    @Operation(
            summary = "음료의 정보를 조회하는 API",
            description = "음료의 unique id, 음료의 이름, 음료의 가격을 조회"
    )
    public ApiResponse<BeverageInfoResponseDto> getCoffee(@PathVariable Long beverageId) {
        // TODO: Apply service and change return value null.
        return ApiResponse.successOf(HttpStatus.OK, null);
    }

    @GetMapping("/all")
    @Operation(
            summary = "모든 음료의 정보를 조회하는 API",
            description = "모든 음료의 unique id, 음료의 이름, 음료의 가격을 조회"
    )
    public ApiResponse<ArrayList<BeverageInfoResponseDto>> getCoffees() {
        // TODO: Apply service and change return value null.
        return ApiResponse.successOf(HttpStatus.OK, null);
    }

    @GetMapping("/popular-beverages")
    @Operation(
            summary = "최근 7일간 인기 있는 커피 메뉴 3개를 조회하는 API",
            description = "커피 id, 커피 이름, 커피 판매 횟수 3개를 반환"
    )
    public ApiResponse<ArrayList<BeverageSaleResponseDto>> getPopularCoffees() {
        // TODO: Apply service and change return value null.
        return ApiResponse.successOf(HttpStatus.OK, null);
    }

}