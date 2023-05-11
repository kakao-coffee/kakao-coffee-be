package com.kakaoCoffee.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderRequestDto {

    @Schema(example = "13")
    private Long beverageId;

    @Schema(example = "21")
    private Long memberName;

}
