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

    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }


}
