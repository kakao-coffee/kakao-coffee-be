package com.kakaoCoffee.beverage.dto;

import com.kakaoCoffee.common.entity.Beverage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BeverageInfoResponseDto {

    @Schema(example = "13")
    private Long beverageId;

    @Schema(example = "Americano")
    private String beverageName;

    @Schema(example = "5000")
    private Long beverageCost;

    public static BeverageInfoResponseDto from(Beverage beverage) {
        return BeverageInfoResponseDto.builder()
                .beverageId(beverage.getId())
                .beverageName(beverage.getBeverageName())
                .beverageCost(beverage.getCost())
                .build();
    }

}
