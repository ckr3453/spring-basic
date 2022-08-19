package com.inflearn.demo.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA : A사용자가 10000원 상당의 물품을 주문
        int userA = statefulService.order("userA", 10000);

        // ThreadB : B사용자가 20000원 상당의 물품을 주문
        int userB = statefulService2.order("userB", 20000);


//        int price = statefulService.getPrice();
        System.out.println("userAprice = " + userA);

        // ThreadA : 사용자A의 주문 금액을 조회 (10000원이 호출되어야하나 중간에 userB가 끼어들어서 덮어써짐)
//        Assertions.assertThat(statefulService.getPrice()).isNotEqualTo(10000);
        Assertions.assertThat(userA).isEqualTo(10000);
    }


    static class TestConfig {
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }

    }

}