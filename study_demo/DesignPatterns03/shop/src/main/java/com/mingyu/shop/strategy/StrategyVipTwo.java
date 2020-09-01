package com.mingyu.shop.strategy;

import org.springframework.stereotype.Component;

/**
 * @date: 2020/8/28 9:19
 * @author: GingJingDM
 * @version: 1.0
 */
@Component("strategyVipTwo")
public class StrategyVipTwo implements StrategyVip {

    /**
     * 白银会员优惠5元
     *
     * @param payMoney 支付金额
     * @return
     */
    @Override
    public Integer payMoney(Integer payMoney) {
        return payMoney - 5;
    }
}
