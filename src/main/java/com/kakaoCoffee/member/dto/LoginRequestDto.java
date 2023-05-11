package com.kakaoCoffee.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class LoginRequestDto {

    @Schema(example = "MyIdIsThis")
    private String memberName;

    @Schema(example = "password")
    private String password;

}
