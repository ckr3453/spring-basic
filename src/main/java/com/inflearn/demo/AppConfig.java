package com.inflearn.demo;

import com.inflearn.demo.discount.DiscountPolicy;
import com.inflearn.demo.discount.FixDiscountPolicy;
import com.inflearn.demo.discount.RateDiscountPolicy;
import com.inflearn.demo.member.MemberService;
import com.inflearn.demo.member.MemberServiceImpl;
import com.inflearn.demo.member.MemoryMemberRepository;
import com.inflearn.demo.order.OrderService;
import com.inflearn.demo.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // @Bean memberService 호출 시 new MemoryMemberRepository()를 호출하게 됨.
    // @Bean orderService 호출 시 new MemoryMemberRepository()를 호출하게 됨.
    // 싱글톤이 깨지는것 처럼 보임 (new 키워드를 사용)

    // 예상출력 (싱글톤 적용안됨)
    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.memberRepository
    // call AppConfig.orderService
    // call AppConfig.memberRepository

    // 실제출력 (싱글톤 적용)
    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.orderService


    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }


}
