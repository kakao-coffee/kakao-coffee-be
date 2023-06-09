package com.kakaoCoffee.member;

import com.kakaoCoffee.common.customEnum.ErrorMessage;
import com.kakaoCoffee.common.entity.Member;
import com.kakaoCoffee.common.repository.MemberRepository;
import com.kakaoCoffee.db.DatabaseTestUtil;
import com.kakaoCoffee.member.customEnum.MemberRoleEnum;
import com.kakaoCoffee.member.service.MemberService;
import com.kakaoCoffee.pointHistory.dto.PointChargeRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ActiveProfiles("test")
@SpringBootTest
@Slf4j
public class MemberIntegrationTests {

    private Member savedMember;

    @Autowired
    private DatabaseTestUtil databaseTestUtil;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        Member newMember = new Member(1L, "memberName", "password", 0L, MemberRoleEnum.USER, "nickName", "member@gmail.com", LocalDate.now());
        this.savedMember = memberRepository.save(newMember);
    }

    @Test
    void shouldChargeAllThePoints() {
        // given
        PointChargeRequestDto pointChargeRequestDto = new PointChargeRequestDto(10L);

        // when
        CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> memberService.chargePoint(pointChargeRequestDto, this.savedMember.getMemberName())),
                CompletableFuture.runAsync(() -> memberService.chargePoint(pointChargeRequestDto, this.savedMember.getMemberName())),
                CompletableFuture.runAsync(() -> memberService.chargePoint(pointChargeRequestDto, this.savedMember.getMemberName()))
        ).join();

        // then
        Member updatedMember = memberRepository.findByMemberName(savedMember.getMemberName()).orElseThrow(
                () -> new EntityNotFoundException(ErrorMessage.MEMBER_NOT_FOUND.getMessage())
        );
        assertEquals(pointChargeRequestDto.getPointAmount() * 3, updatedMember.getPoint());
    }

    @AfterEach
    public void setDown() {
        databaseTestUtil.cleanUp();
    }

}
