package com.kakaoCoffee.coffee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
public class PopularCoffeesResponseDto {

    private ArrayList<CoffeeSaleResponseDto> popularCoffees;

}
