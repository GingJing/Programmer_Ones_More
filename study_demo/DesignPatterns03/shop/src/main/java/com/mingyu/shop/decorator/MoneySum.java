package com.mingyu.shop.decorator;


import com.mingyu.shop.domain.Order;

public interface MoneySum {

    /***
     * 订单价格计算 --- [结算]
     */
    void sum(Order order);

}
