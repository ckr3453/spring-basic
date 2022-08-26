package com.inflearn.demo.web;

import com.inflearn.demo.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor    // 생성자를 통한 주입
public class LogDemoController {

    private final LogDemoService logDemoService;
    // 스프링 컨테이너가 의존관계를 주입할 때 필요한데 scope=request여서 존재하지 않음 (http request가 있을때만 살아있음)
    // 그래서 Provider를 통해 스프링 컨테이너에 빈 생성을 지연 요청
    // 굳이 Provider를 써야하나?? -> 대체 방법 : 프록시
    // CGLIB으로 내 클래스를 상속받은 가짜 프록시 객체를 만들어서 주입함.
//    private final ObjectProvider<MyLogger> myLoggerProvider;
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        // 원래는 스프링 인터셉터에서 구현해야함 (공통으로)
        String requestURL = request.getRequestURL().toString();
//        MyLogger myLogger = myLoggerProvider.getObject();


        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        Thread.sleep(1000);     // 중간에 다른요청이 와도 기존값이 유지됨 (프로토타입)
        logDemoService.logic("test id");

        return "OK";
    }
}
