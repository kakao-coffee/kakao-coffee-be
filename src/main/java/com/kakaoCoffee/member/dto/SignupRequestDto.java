package com.kakaoCoffee.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
public class SignupRequestDto {

    @Schema(example = "memberName")
    private String memberName;

    @Schema(example = "nickName")
    private String nickName;

    @Schema(example = "password")
    private String password;

    @Schema(example = "memberName@gmail.com")
    private String email;

    private LocalDate birthday;

}
