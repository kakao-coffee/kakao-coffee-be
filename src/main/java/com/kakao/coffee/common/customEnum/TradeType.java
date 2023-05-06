package com.kakao.coffee.common.customEnum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TradeType {

    CHARGE("포인트 충전"),
    SPEND("포인트 사용");

    String description;

    TradeType(String description) {
        this.description = description;
    }

}
