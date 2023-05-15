package com.kakaoCoffee.beverage;

import com.kakaoCoffee.beverage.dto.BeverageSaleResponseDto;
import com.kakaoCoffee.common.entity.Beverage;
import com.kakaoCoffee.common.entity.Member;
import com.kakaoCoffee.common.entity.Order;
import com.kakaoCoffee.common.repository.BeverageRepository;
import com.kakaoCoffee.common.repository.MemberRepository;
import com.kakaoCoffee.common.repository.OrderRepository;
import com.kakaoCoffee.member.customEnum.MemberRoleEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
@Slf4j
public class BeverageRepositoryTests {

    private Member savedMember;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BeverageRepository beverageRepository;

    @BeforeEach
    void setUp() {
        // setting menu and member
        Member newMember = new Member(1L, "memberName", "password", 100000L, MemberRoleEnum.USER, "nickName", "member@gmail.com", LocalDate.now());
        this.savedMember = memberRepository.save(newMember);

    }

    @Test
    void shouldGetMostPopular3BeveragesAsDesc() {
        // given
        List<Beverage> savedBeverages = new ArrayList<>();
        savedBeverages.add(beverageRepository.save(Beverage.create("Americano", 2000L)));
        savedBeverages.add(beverageRepository.save(Beverage.create("Cafe latte", 1500L)));
        savedBeverages.add(beverageRepository.save(Beverage.create("Lemonade", 1500L)));

        // when
        orderRepository.save(Order.create(savedMember, savedBeverages.get(0), savedBeverages.get(0).getCost()));
        orderRepository.save(Order.create(savedMember, savedBeverages.get(0), savedBeverages.get(0).getCost()));
        orderRepository.save(Order.create(savedMember, savedBeverages.get(0), savedBeverages.get(0).getCost()));

        orderRepository.save(Order.create(savedMember, savedBeverages.get(1), savedBeverages.get(1).getCost()));
        orderRepository.save(Order.create(savedMember, savedBeverages.get(1), savedBeverages.get(1).getCost()));

        orderRepository.save(Order.create(savedMember, savedBeverages.get(2), savedBeverages.get(2).getCost()));

        // then
        List<BeverageSaleResponseDto> beverageSaleResponseDtos = beverageRepository.getPopularBeverages();
        assertEquals(3L, beverageSaleResponseDtos.get(0).getSaleCount());
        assertEquals(2L, beverageSaleResponseDtos.get(1).getSaleCount());
        assertEquals(1L, beverageSaleResponseDtos.get(2).getSaleCount());
    }

}
