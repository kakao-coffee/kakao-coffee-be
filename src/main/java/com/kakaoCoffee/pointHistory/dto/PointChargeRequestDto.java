package com.kakaoCoffee.pointHistory.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PointChargeRequestDto {

    @Schema(example = "21")
    private Long memberId;

    @Schema(example = "5000")
    private Long pointAmount;

}
