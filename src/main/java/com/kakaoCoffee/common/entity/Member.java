package com.kakaoCoffee.common.entity;

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

    @Column(name = "point", nullable = false)
    private Long point;

    @Column(name = "member_name", nullable = false, unique = true)
    private String memberName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nick_name", nullable = false)
    private String nickName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    public static Member of(SignupRequestDto signupRequestDto, String encodedPassword) {
        return Member.builder()
                .point(0L)
                .memberName(signupRequestDto.getMemberName())
                .password(encodedPassword)
                .nickName(signupRequestDto.getNickName())
                .email(signupRequestDto.getEmail())
                .birthday(signupRequestDto.getBirthday())
                .build();
    }

}
