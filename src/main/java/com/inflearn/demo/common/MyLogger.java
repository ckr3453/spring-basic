package com.inflearn.demo.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.println("[" + this.uuid + "]" + "[" + this.requestURL + "] " + message);
    }

    // 스프링 빈 생성 후 콜백
    @PostConstruct
    public void init() {
        this.uuid = UUID.randomUUID().toString();
        System.out.println("[" + this.uuid + "] request scope bean create: "+ this);
    }

    // 소멸 전 콜백
    @PreDestroy
    public void close() {
        System.out.println("[" + this.uuid + "] request scope bean close: "+ this);
    }
}
