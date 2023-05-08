package com.kakaoCoffee.beverage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BeverageInfoResponseDto {

    @Schema(example = "13")
    private Long beverageId;

    @Schema(example = "Americano")
    private String beverageName;

    @Schema(example = "5000")
    private Long beverageCost;

}
