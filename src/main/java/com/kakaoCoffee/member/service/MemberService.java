package com.kakaoCoffee.member.service;

import com.kakaoCoffee.common.dto.ErrorMessage;
import com.kakaoCoffee.common.entity.Member;
import com.kakaoCoffee.common.jwt.JwtUtil;
import com.kakaoCoffee.common.repository.MemberRepository;
import com.kakaoCoffee.member.customEnum.MemberRoleEnum;
import com.kakaoCoffee.member.dto.LoginRequestDto;
import com.kakaoCoffee.member.dto.MemberResponseDto;
import com.kakaoCoffee.member.dto.SignupRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponseDto signup(SignupRequestDto signupRequestDto) {
        String memberId = signupRequestDto.getMemberId();
        String encodedPassword = passwordEncoder.encode(signupRequestDto.getPassword());

        // memberId (member ID) 중복 확인
        Optional<Member> found = memberRepository.findByMemberId(memberId);
        if (found.isPresent()) {
            throw new IllegalArgumentException(ErrorMessage.USERID_DUPLICATION.getMessage());
        }

        Member newMember = Member.from(signupRequestDto, encodedPassword, MemberRoleEnum.USER);
        newMember = memberRepository.save(newMember);

        return MemberResponseDto.from(newMember);
    }

    @Transactional(readOnly = true)
    public MemberResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse response) {

        // 사용자 확인
        Member member = memberRepository.findByMemberId(loginRequestDto.getMemberId()).orElseThrow(
                () -> new EntityNotFoundException(ErrorMessage.WRONG_USERID.getMessage())
        );

        // 비밀번호 확인
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), member.getPassword())) {
            throw new BadCredentialsException(ErrorMessage.WRONG_PASSWORD.getMessage());
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(member.getMemberId()));

        return MemberResponseDto.from(member);
    }

    public Boolean checkMemberIdDuplication(String memberId) {
        return memberRepository.findByMemberId(memberId).isPresent();
    }

}
