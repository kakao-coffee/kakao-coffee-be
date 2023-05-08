package com.kakaoCoffee.coffee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CoffeeSaleResponseDto {

    @Schema(example = "3")
    private Long coffeeId;

    @Schema(example = "Americano")
    private String coffeeName;

    @Schema(example = "10")
    private Long saleCount;

}
