package com.inflearn.demo.singleton;

import com.inflearn.demo.AppConfig;
import com.inflearn.demo.member.MemberRepository;
import com.inflearn.demo.member.MemberService;
import com.inflearn.demo.member.MemberServiceImpl;
import com.inflearn.demo.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    @DisplayName("자바로 설정정보 구현 시 싱글톤 패턴이 깨지는지 (깨지지않는다)")
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memoryMemberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository = " + memberRepository);
        System.out.println("orderService -> memberRepository2 = " + memberRepository2);
        System.out.println("memoryMemberRepository = " + memoryMemberRepository);

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memoryMemberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memoryMemberRepository);

    }

    @Test
    @DisplayName("@Configuration 사용시 CGLIB으로 인해 스프링 컨테이너에서 싱글톤 패턴으로 반환하도록 만들어짐")
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
    }
}
