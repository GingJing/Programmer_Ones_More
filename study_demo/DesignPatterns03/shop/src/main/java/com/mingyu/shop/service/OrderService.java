package com.mingyu.shop.service;


import com.mingyu.shop.domain.Order;

public interface OrderService {
    /***
     * 添加订单
     * @param order
     */
    int add(Order order);

    /***
     * 取消订单
     * @param id
     */
    void cancelOrder(String id);
}
