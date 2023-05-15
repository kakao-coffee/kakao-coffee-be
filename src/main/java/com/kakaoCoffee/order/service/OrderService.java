package com.kakaoCoffee.order.service;

import com.kakaoCoffee.common.customEnum.ErrorMessage;
import com.kakaoCoffee.common.customEnum.TradeType;
import com.kakaoCoffee.common.entity.Beverage;
import com.kakaoCoffee.common.entity.Member;
import com.kakaoCoffee.common.entity.Order;
import com.kakaoCoffee.common.entity.PointHistory;
import com.kakaoCoffee.common.repository.BeverageRepository;
import com.kakaoCoffee.common.repository.MemberRepository;
import com.kakaoCoffee.common.repository.OrderRepository;
import com.kakaoCoffee.common.repository.PointHistoryRepository;
import com.kakaoCoffee.order.dto.OrderRequestDto;
import com.kakaoCoffee.order.dto.OrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final MemberRepository memberRepository;

    private final BeverageRepository beverageRepository;

    private final OrderRepository orderRepository;

    private final PointHistoryRepository pointHistoryRepository;

    @Transactional
    public OrderResponseDto orderBeverage(OrderRequestDto orderRequestDto, String memberName) {
        Member member = memberRepository.findByMemberName(memberName).orElseThrow(
                () -> new EntityNotFoundException(ErrorMessage.MEMBER_NOT_FOUND.getMessage())
        );

        Beverage beverage = beverageRepository.findById(orderRequestDto.getBeverageId()).orElseThrow(
                () -> new EntityNotFoundException(ErrorMessage.BEVERAGE_NOT_FOUND.getMessage())
        );

        Long cost = beverage.getCost();

        if (member.getPoint() < cost) {
            throw new IllegalArgumentException(ErrorMessage.NOT_ENOUGH_POINT.getMessage());
        } else {
            member.setPoint(member.getPoint() - cost);
        }

        Order newOrder = orderRepository.save(Order.create(member, beverage, cost));

        pointHistoryRepository.save(PointHistory.create(member, cost, TradeType.SPEND));

        return OrderResponseDto.create(member, newOrder);
    }

}
