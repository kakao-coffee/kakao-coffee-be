package com.kakaoCoffee.common.entity;

import com.kakaoCoffee.member.customEnum.MemberRoleEnum;
import com.kakaoCoffee.member.dto.SignupRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "members")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", nullable = false, unique = true)
    private String memberId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "point", nullable = false)
    private Long point;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private MemberRoleEnum role;

    @Column(name = "nick_name", nullable = false)
    private String nickName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    public static Member from(SignupRequestDto signupRequestDto, String encodedPassword, MemberRoleEnum memberRoleEnum) {
        return Member.builder()
                .memberId(signupRequestDto.getMemberId())
                .password(encodedPassword)
                .point(0L)
                .role(memberRoleEnum)
                .nickName(signupRequestDto.getNickName())
                .email(signupRequestDto.getEmail())
                .birthday(signupRequestDto.getBirthday())
                .build();
    }

}
