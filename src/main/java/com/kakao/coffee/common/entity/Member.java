package com.kakao.coffee.common.entity;

import com.kakao.coffee.member.dto.SignupRequestDto;
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

    @Column(nullable = false, unique = true)
    private String memberName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDate birthday;

    public static Member of(SignupRequestDto signupRequestDto, String encodedPassword) {
        return Member.builder()
                .memberName(signupRequestDto.getMemberName())
                .password(encodedPassword)
                .nickName(signupRequestDto.getNickName())
                .email(signupRequestDto.getEmail())
                .birthday(signupRequestDto.getBirthday())
                .build();
    }

}
