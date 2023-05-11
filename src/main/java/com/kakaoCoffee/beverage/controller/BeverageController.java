package com.kakaoCoffee.beverage.controller;

import com.kakaoCoffee.beverage.dto.BeverageInfoResponseDto;
import com.kakaoCoffee.beverage.dto.BeverageSaleResponseDto;
import com.kakaoCoffee.beverage.service.BeverageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "커피 메뉴 조회 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/beverages")
public class BeverageController {

    private final BeverageService beverageService;

    @GetMapping
    @Operation(
            summary = "모든 음료의 정보를 조회하는 API",
            description = "모든 음료의 unique id, 음료의 이름, 음료의 가격을 조회"
    )
    public ResponseEntity<List<BeverageInfoResponseDto>> getAllBeverages() {
        return ResponseEntity.ok(beverageService.getAllBeverages());
    }

    @GetMapping("/popular-beverages")
    @Operation(
            summary = "최근 7일간 인기 있는 커피 메뉴 3개를 조회하는 API",
            description = "커피 id, 커피 이름, 커피 판매 횟수 3개를 반환"
    )
    public ResponseEntity<List<BeverageSaleResponseDto>> getPopularBeverages() {
        return ResponseEntity.ok(beverageService.getPopularBeverages());
    }

}
