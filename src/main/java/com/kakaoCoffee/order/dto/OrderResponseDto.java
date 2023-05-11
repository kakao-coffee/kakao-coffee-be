package com.kakaoCoffee.order.dto;

import com.kakaoCoffee.common.entity.Member;
import com.kakaoCoffee.common.entity.Order;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderResponseDto {

    @Schema(example = "member")
    private String memberId;

    @Schema(example = "13")
    private Long beverageId;

    @Schema(example = "Americano")
    private String beverageName;

    @Schema(example = "5000")
    private Long orderCost;

    public static OrderResponseDto from(Member member, Order order) {
        return OrderResponseDto.builder()
                .memberId(member.getMemberId())
                .beverageId(order.getBeverage().getId())
                .beverageName(order.getBeverage().getBeverageName())
                .orderCost(order.getBeverage().getCost())
                .build();
    }

}
