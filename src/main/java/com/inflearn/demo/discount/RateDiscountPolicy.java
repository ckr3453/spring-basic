package com.inflearn.demo.discount;

import com.inflearn.demo.annotation.MainDiscountPolicy;
import com.inflearn.demo.member.Grade;
import com.inflearn.demo.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary // 동일한 빈이 2개이상 일 시 우선권을 가진다.
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
