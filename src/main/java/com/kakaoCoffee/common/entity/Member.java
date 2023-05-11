package com.kakaoCoffee.common.entity;

import com.kakaoCoffee.member.customEnum.MemberRoleEnum;
import com.kakaoCoffee.member.dto.SignupRequestDto;
import lombok.*;

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

    @Column(name = "member_name", nullable = false, unique = true)
    private String memberName;

    @Column(name = "password", nullable = false)
    private String password;

    @Setter
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
                .memberName(signupRequestDto.getMemberName())
                .password(encodedPassword)
                .point(0L)
                .role(memberRoleEnum)
                .nickName(signupRequestDto.getNickName())
                .email(signupRequestDto.getEmail())
                .birthday(signupRequestDto.getBirthday())
                .build();
    }

}
