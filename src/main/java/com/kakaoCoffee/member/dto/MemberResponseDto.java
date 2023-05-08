package com.kakaoCoffee.member.dto;

import com.kakaoCoffee.common.entity.Member;
import com.kakaoCoffee.common.entity.Timestamped;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class MemberResponseDto extends Timestamped {

    @Schema(type = "integer", example = "2")
    private Long id;

    @Schema(example = "userId")
    private String userId;

    @Schema(example = "apple123")
    private String username;

    @Schema(example = "user@gmail.com")
    private String email;

    @Schema(example = "user@gmail.com")
    private LocalDate birthday;

    public static MemberResponseDto of(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .username(member.getNickName())
                .userId(member.getMemberName())
                .email(member.getEmail())
                .birthday(member.getBirthday())
                .build();
    }

}
