package com.inflearn.demo.order;

import com.inflearn.demo.discount.DiscountPolicy;
import com.inflearn.demo.discount.FixDiscountPolicy;
import com.inflearn.demo.member.Member;
import com.inflearn.demo.member.MemberRepository;
import com.inflearn.demo.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); // 단일 책임 원칙을 준수함. (만일 할인률을 손봐야 할때면 굳이 다른 서비스를 건드릴 필요가없음.

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
