package com.inflearn.demo;

import com.inflearn.demo.discount.FixDiscountPolicy;
import com.inflearn.demo.member.MemberService;
import com.inflearn.demo.member.MemberServiceImpl;
import com.inflearn.demo.member.MemoryMemberRepository;
import com.inflearn.demo.order.OrderService;
import com.inflearn.demo.order.OrderServiceImpl;

public class AppConfig {

    // 애플리케이션의 실제 동작에 필요한 구현 객체를 생성한다.
    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService(){
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }


}
