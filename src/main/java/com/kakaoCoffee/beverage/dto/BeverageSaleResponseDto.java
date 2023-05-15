package com.kakaoCoffee.beverage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BeverageSaleResponseDto {

    @Schema(example = "3")
    private Long beverageId;

    @Schema(example = "Americano")
    private String beverageName;

    @Schema(example = "10")
    private Long saleCount;

}
