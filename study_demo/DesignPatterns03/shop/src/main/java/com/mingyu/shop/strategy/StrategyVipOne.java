package com.mingyu.shop.strategy;

import org.springframework.stereotype.Component;

/**
 * @date: 2020/8/28 9:18
 * @author: GingJingDM
 * @version: 1.0
 */
@Component("strategyVipOne")
public class StrategyVipOne implements StrategyVip {

    /**
     * 普通会员，无优惠
     * @param payMoney 支付金额
     * @return 优惠后金额
     */
    @Override
    public Integer payMoney(Integer payMoney) {
        return payMoney;
    }
}
