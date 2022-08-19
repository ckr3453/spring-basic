package com.inflearn.demo.singleton;

public class SingletonService {

    // static 영역에 객체를 1개만 생성한다.
    private static final SingletonService instance = new SingletonService(); // 자기자신을 static에 올림

    // 객체 인스턴스가 필요하면 해당 메서드를 통해서만 조회하도록 허용한다.
    public static SingletonService getInstance(){
        return instance;
    }

    // 생성자를 private로 선언하여 외부에서 new 키워드를 사용한 객체 생성을 제한한다. (자기 자신만 생성자 호출 가능)
    private SingletonService(){
    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

}
