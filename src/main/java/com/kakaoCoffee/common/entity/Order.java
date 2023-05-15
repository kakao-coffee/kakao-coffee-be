package com.kakaoCoffee.common.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "orders")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Order extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "beverage_id")
    private Beverage beverage;

    @Column(name = "cost", nullable = false)
    private Long cost;

    public static Order from(Member member, Beverage beverage, Long cost) {
        return Order.builder()
                .member(member)
                .beverage(beverage)
                .cost(cost)
                .build();
    }

}
