package com.kakaoCoffee.common.dto;

import com.kakaoCoffee.common.customEnum.ErrorType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(access = AccessLevel.PRIVATE)
public class ErrorResponseDto {

    ErrorType errorType;

    String errorMessage;

    public static ErrorResponseDto of(ErrorType errorType, String errorMessage) {
        return ErrorResponseDto.builder()
                .errorType(errorType)
                .errorMessage(errorMessage)
                .build();
    }
}
