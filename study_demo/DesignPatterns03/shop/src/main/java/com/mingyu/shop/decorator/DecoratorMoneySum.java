package com.mingyu.shop.decorator;

import com.mingyu.shop.domain.Order;

/**
 * 订单金额计算装饰类
 *
 * @date: 2020/8/28 8:54
 * @author: GingJingDM
 * @version: 1.0
 */
public abstract class DecoratorMoneySum implements MoneySum {

    private MoneySum moneySum;

    public void setMoneySum(MoneySum moneySum) {
        this.moneySum = moneySum;
    }

    @Override
    public void sum(Order order) {
        moneySum.sum(order);
    }
}
