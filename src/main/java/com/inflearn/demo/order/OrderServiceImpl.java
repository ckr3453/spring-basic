package com.inflearn.demo.order;

import com.inflearn.demo.discount.DiscountPolicy;
import com.inflearn.demo.member.Member;
import com.inflearn.demo.member.MemberRepository;
import com.inflearn.demo.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component  // 컴포넌트 스캔 대상
@RequiredArgsConstructor    // 롬복으로 인자가 있는 생성자 자동생성
public class OrderServiceImpl implements OrderService{

    /* 생성자로 주입 (권장하는 방식) */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /* setter로 주입 (필드가 수정이 가능하며 불멸, 필수가 아닌경우 사용) */
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;
//
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

    /* 필드로 주입 (권장하지 않음) */
    // 간결한 방법이지만 외부에서 변경이 불가능하기 때문에 테스트가 어려움
//    @Autowired private MemberRepository memberRepository;
//    @Autowired private DiscountPolicy discountPolicy;

    /* 일반 메서드로 주입 (잘 사용하지 않음) */
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;
//
//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy){
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

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
