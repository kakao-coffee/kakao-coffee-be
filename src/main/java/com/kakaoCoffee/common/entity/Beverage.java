package com.kakaoCoffee.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "beverages")
@Getter
@Builder
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

    public static Beverage of(String beverageName, Long cost) {
        return Beverage.builder()
                .beverageName(beverageName)
                .cost(cost)
                .build();
    }

}
