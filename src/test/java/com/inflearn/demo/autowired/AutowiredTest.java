package com.inflearn.demo.autowired;

import com.inflearn.demo.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    @DisplayName("@Autowired option 테스트")
    void autowiredOptionTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }


    static class TestBean {
        // Member는 빈으로 등록되지 않아서 true는 오류

        // required = false (의존관계가 없으면 메소드 자체가 호출이 되지않음)
        @Autowired(required = false)
        public void setNoBean1(Member noBean1){
            System.out.println("noBean1 = " + noBean1);
        }

        // null로 출력
        @Autowired
        public void setNoBean2(@Nullable Member noBean2){
            System.out.println("noBean2 = " + noBean2);
        }

        // Optional.empty로 출력
        @Autowired
        public void setNoBean3(Optional<Member> noBean3){
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
