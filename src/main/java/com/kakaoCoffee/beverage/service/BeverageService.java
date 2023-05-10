package com.kakaoCoffee.beverage.service;

import com.kakaoCoffee.beverage.dto.BeverageInfoResponseDto;
import com.kakaoCoffee.common.entity.Beverage;
import com.kakaoCoffee.common.repository.BeverageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BeverageService {

    private final BeverageRepository beverageRepository;

    @Transactional
    public List<BeverageInfoResponseDto> getAllBeverages() {
        return beverageRepository.findAll()
                .stream()
                .map(beverage -> BeverageInfoResponseDto.from(beverage))
                .collect(Collectors.toList());
    }

}
