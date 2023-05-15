package com.kakaoCoffee.common.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "beverages")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Beverage extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "beverage_name", nullable = false, unique = true, length = 30)
    private String beverageName;

    @Column(name = "cost", nullable = false)
    private Long cost;

    public static Beverage create(String beverageName, Long cost) {
        return Beverage.builder()
                .beverageName(beverageName)
                .cost(cost)
                .build();
    }

}
