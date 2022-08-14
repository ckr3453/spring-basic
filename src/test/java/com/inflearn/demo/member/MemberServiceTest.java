package com.inflearn.demo.member;

import com.inflearn.demo.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    // 테스트 실행전에 실행
    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join(){
        //given (무엇인가가 주어졌을때)
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when (어딘가에서 실행됐을때)
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then (결과를 검증)
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
