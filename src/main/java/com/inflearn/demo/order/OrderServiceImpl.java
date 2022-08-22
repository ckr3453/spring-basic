package com.inflearn.demo.order;

import com.inflearn.demo.discount.DiscountPolicy;
import com.inflearn.demo.member.Member;
import com.inflearn.demo.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component  // 컴포넌트 스캔 대상
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired  // 자동으로 의존관계주입
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); // 단일 책임 원칙을 준수함. (만일 할인률을 손봐야 할때면 굳이 다른 서비스를 건드릴 필요가없음.

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트용 (싱글톤 패턴이 깨지는지)
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
