package com.kakaoCoffee.common.repository;

import com.kakaoCoffee.beverage.dto.BeverageSaleResponseDto;
import com.kakaoCoffee.common.entity.Beverage;
import com.kakaoCoffee.common.entity.QBeverage;
import com.kakaoCoffee.common.entity.QOrder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BeverageRepositoryImpl implements BeverageRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<BeverageSaleResponseDto> getPopularBeverages() {
        QOrder order = QOrder.order;
        QBeverage beverage = QBeverage.beverage;

        // Get the current date and subtract 7 days
        LocalDateTime startDate = LocalDateTime.now().minusDays(7);

        List<BeverageSaleResponseDto> popularBeverages = jpaQueryFactory
                .select(Projections.constructor(
                        BeverageSaleResponseDto.class, beverage.id, beverage.beverageName, order.count()
                ))
                .from(beverage)
                .join(order).on(order.beverage.eq(beverage))
                .where(order.createdAt.goe(startDate))
                .groupBy(beverage.id)
                .orderBy(order.count().desc())
                .limit(3)
                .fetch();

        return popularBeverages;
    }

}
