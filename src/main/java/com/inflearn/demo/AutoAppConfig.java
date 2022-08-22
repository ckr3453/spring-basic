package com.inflearn.demo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan( // 프로젝트 루트 경로에 두는것이 좋음
        // base 옵션을 적용하지 않으면 @ComponentScan 대상의 패키지(하위 포함)를 default로 스캔
//        basePackages = {"com.inflearn.demo.member"},  // 컴포넌트 스캔 영역을 해당 패키지(하위 포함)로 제한 (여러개도 가능)
//        basePackageClasses = AutoAppConfig.class,     // 컴포넌트 스캔 영역을 지정한 클래스(하위 포함)로 제한
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) // AppConfig, TestConfig 제외
)
public class AutoAppConfig {

}
