package com.kakaoCoffee.common.customEnum;

public enum ErrorMessage {
    
    // Member entity 관련 오류
    MEMBER_NOT_FOUND("해당 사용자가 존재하지 않습니다."),
    MEMBER_NAME_DUPLICATION("memberName이 중복됐습니다."),
    WRONG_MEMBER_NAME("memberName이 일치하지 않습니다."),
    WRONG_ADMIN_PASSWORD("관리자 패스워드가 틀려 등록이 불가능합니다."),
    WRONG_PASSWORD("패스워드가 틀렸습니다."),
    WRONG_JWT_TOKEN("JWT Token이 잘못되었습니다."),
    AUTHENTICATION_FAILED("JWT가 올바르지 않습니다"),
    ACCESS_DENIED("권한이 없습니다."),
    
    // Beverage entity 관련 오류
    BEVERAGE_NOT_FOUND("해당 음료가 존재하지 않습니다."),

    // Point Charge 관련 오류
    NOT_ENOUGH_POINT("포인트가 부족합니다.");

    String message;

    ErrorMessage(String description) {
        this.message = description;
    }

    public String getMessage() {
        return this.message;
    }
}
