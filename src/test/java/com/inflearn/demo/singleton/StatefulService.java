package com.inflearn.demo.singleton;

public class StatefulService {

//    private int price; // 상태를 유지하는 필드 (10000 -> 20000)

    // 주문을 하면 값을 this에 저장
    public int order(String name, int price){
        System.out.println("name = " + name + " price = "+price);
//        this.price = price; // 문제 발생
        return price;
    }

    // 가격 호출
//    public int getPrice(){
//        return price;
//    }
}
