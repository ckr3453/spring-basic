package com.inflearn.demo.scan;

import com.inflearn.demo.AutoAppConfig;
import com.inflearn.demo.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class AutoAppConfigTest {

    @Test
    @DisplayName("컴포넌트 스캔 테스트")
    void basicScan(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService bean = ac.getBean(MemberService.class);
        assertThat(bean).isInstanceOf(MemberService.class);
    }
}
