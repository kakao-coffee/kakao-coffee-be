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

    @Schema(example = "apple123")
    private String memberId;

    @Schema(example = "15000")
    private Long point;

    @Schema(example = "apple121")
    private String nickName;

    @Schema(example = "user@gmail.com")
    private String email;

    private LocalDate birthday;

    public static MemberResponseDto from(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .memberId(member.getMemberId())
                .point(member.getPoint())
                .nickName(member.getNickName())
                .email(member.getEmail())
                .birthday(member.getBirthday())
                .build();
    }

}
