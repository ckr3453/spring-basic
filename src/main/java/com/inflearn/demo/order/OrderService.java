package com.inflearn.demo.order;

public interface OrderService {
    Order createOrder(Long memberId, String ItemName, int itemPrice);
}
