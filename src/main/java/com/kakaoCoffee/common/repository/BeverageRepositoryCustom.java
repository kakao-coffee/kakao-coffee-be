package com.kakaoCoffee.common.repository;

import com.kakaoCoffee.beverage.dto.BeverageSaleResponseDto;
import com.kakaoCoffee.common.entity.Beverage;

import java.util.List;

public interface BeverageRepositoryCustom {

    List<BeverageSaleResponseDto> getPopularBeverages();

}
