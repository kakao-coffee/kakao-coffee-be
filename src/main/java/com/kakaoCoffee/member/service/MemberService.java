package com.kakaoCoffee.member.service;

import com.kakaoCoffee.common.customEnum.ErrorMessage;
import com.kakaoCoffee.common.customEnum.TradeType;
import com.kakaoCoffee.common.entity.Member;
import com.kakaoCoffee.common.entity.PointHistory;
import com.kakaoCoffee.common.jwt.JwtUtil;
import com.kakaoCoffee.common.repository.MemberRepository;
import com.kakaoCoffee.common.repository.PointHistoryRepository;
import com.kakaoCoffee.member.customEnum.MemberRoleEnum;
import com.kakaoCoffee.member.dto.LoginRequestDto;
import com.kakaoCoffee.member.dto.MemberResponseDto;
import com.kakaoCoffee.member.dto.SignupRequestDto;
import com.kakaoCoffee.pointHistory.dto.PointChargeRequestDto;
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

    private final PointHistoryRepository pointHistoryRepository;

    @Transactional
    public MemberResponseDto signup(SignupRequestDto signupRequestDto) {
        String memberName = signupRequestDto.getMemberName();
        String encodedPassword = passwordEncoder.encode(signupRequestDto.getPassword());

        // memberName (member ID) 중복 확인
        Optional<Member> found = memberRepository.findByMemberName(memberName);
        if (found.isPresent()) {
            throw new IllegalArgumentException(ErrorMessage.MEMBER_NAME_DUPLICATION.getMessage());
        }

        Member newMember = Member.create(signupRequestDto, encodedPassword, MemberRoleEnum.USER);
        newMember = memberRepository.save(newMember);

        return MemberResponseDto.create(newMember);
    }

    @Transactional(readOnly = true)
    public MemberResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse response) {

        // 사용자 확인
        Member member = memberRepository.findByMemberName(loginRequestDto.getMemberName()).orElseThrow(
                () -> new EntityNotFoundException(ErrorMessage.WRONG_MEMBER_NAME.getMessage())
        );

        // 비밀번호 확인
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), member.getPassword())) {
            throw new BadCredentialsException(ErrorMessage.WRONG_PASSWORD.getMessage());
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(member.getMemberName()));

        return MemberResponseDto.create(member);
    }

    @Transactional
    public Boolean checkMemberNameDuplication(String memberName) {
        return memberRepository.findByMemberName(memberName).isPresent();
    }

    @Transactional
    public MemberResponseDto chargePoint(PointChargeRequestDto pointChargeRequestDto, String memberName) {
        Member member = memberRepository.findByMemberName(memberName).orElseThrow(
                () -> new EntityNotFoundException(com.kakaoCoffee.common.customEnum.ErrorMessage.MEMBER_NOT_FOUND.getMessage())
        );

        member.setPoint(member.getPoint() + pointChargeRequestDto.getPointAmount());

        pointHistoryRepository.save(PointHistory.create(member, pointChargeRequestDto.getPointAmount(), TradeType.CHARGE));

        return MemberResponseDto.create(member);
    }

}
