package com.kakaoCoffee.beverage;

import com.kakaoCoffee.beverage.dto.BeverageInfoResponseDto;
import com.kakaoCoffee.beverage.service.BeverageService;
import com.kakaoCoffee.common.entity.Beverage;
import com.kakaoCoffee.common.repository.BeverageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BeverageServiceUnitTests {

    @Mock
    BeverageRepository beverageRepository;

    private BeverageService beverageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.beverageService = new BeverageService(this.beverageRepository);
    }

    @Test
    void beverageServiceGetAllBeveragesTest() {
        // given
        List<Beverage> beverages = Arrays.asList(
                Beverage.create("Americano", 2000L),
                Beverage.create("Lemonade", 2500L)
        );
        when(beverageRepository.findAll()).thenReturn(beverages);

        // when
        List<BeverageInfoResponseDto> foundBeverages = beverageService.getAllBeverages();

        // then
        verify(beverageRepository).findAll();
        Assertions.assertEquals(beverages.size(), foundBeverages.size());
    }

}
