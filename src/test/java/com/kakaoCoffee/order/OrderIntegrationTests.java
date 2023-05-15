package com.kakaoCoffee.order;

import com.kakaoCoffee.common.customEnum.ErrorMessage;
import com.kakaoCoffee.common.entity.Beverage;
import com.kakaoCoffee.common.entity.Member;
import com.kakaoCoffee.common.entity.Order;
import com.kakaoCoffee.common.repository.BeverageRepository;
import com.kakaoCoffee.common.repository.MemberRepository;
import com.kakaoCoffee.common.repository.OrderRepository;
import com.kakaoCoffee.db.DatabaseTestUtil;
import com.kakaoCoffee.member.customEnum.MemberRoleEnum;
import com.kakaoCoffee.order.dto.OrderRequestDto;
import com.kakaoCoffee.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@ActiveProfiles("test")
@SpringBootTest
@Slf4j
public class OrderIntegrationTests {

    private Member savedMember;

    private Beverage savedBeverage;

    @Autowired
    private DatabaseTestUtil databaseTestUtil;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BeverageRepository beverageRepository;

    @Autowired
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        Member newMember = new Member(1L, "memberName", "password", 6000L, MemberRoleEnum.USER, "nickName", "member@gmail.com", LocalDate.now());
        this.savedMember = memberRepository.save(newMember);
        this.savedBeverage = beverageRepository.save(Beverage.of("Americano", 2000L));
    }

    @DisplayName("order concurrency test")
    @Test
    void orderConcurrencyTest() {
        // given
        OrderRequestDto orderRequestDto = new OrderRequestDto(savedBeverage.getId());

        // when
        CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> orderService.orderBeverage(orderRequestDto, this.savedMember.getMemberName())),
                CompletableFuture.runAsync(() -> orderService.orderBeverage(orderRequestDto, this.savedMember.getMemberName())),
                CompletableFuture.runAsync(() -> orderService.orderBeverage(orderRequestDto, this.savedMember.getMemberName()))
        ).join();

        // then
        List<Order> orders = orderRepository.findAll();
        Assertions.assertEquals(3L, orders.size());

        Member updatedMember = memberRepository.findByMemberName(savedMember.getMemberName()).orElseThrow(
                () -> new EntityNotFoundException(ErrorMessage.MEMBER_NOT_FOUND.getMessage())
        );
        Assertions.assertEquals(0L, updatedMember.getPoint());
    }

    @AfterEach
    public void setDown() {
        databaseTestUtil.cleanUp();
    }

}
