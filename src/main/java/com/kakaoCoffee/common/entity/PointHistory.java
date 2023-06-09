package com.kakaoCoffee.common.entity;

import com.kakaoCoffee.common.customEnum.TradeType;
import lombok.*;

import javax.persistence.*;

@Entity(name = "point_histories")
@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class PointHistory extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "point_amount", nullable = false)
    private Long pointAmount;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "trade_type", nullable = false, length = 30)
    private TradeType tradeType;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public static PointHistory create(Member member, Long pointAmount, TradeType tradeType) {
        return PointHistory.builder()
                .member(member)
                .pointAmount(pointAmount)
                .tradeType(tradeType)
                .build();
    }

}
