package com.mingyu.shop.strategy;

import org.springframework.stereotype.Component;

/**
 * @date: 2020/8/28 9:19
 * @author: GingJingDM
 * @version: 1.0
 */
@Component("strategyVipThree")
public class StrategyVipThree implements StrategyVip {

    /**
     * 黄金会员打7折
     *
     * @param payMoney 支付金额
     * @return 优惠后支付金额
     */
    @Override
    public Integer payMoney(Integer payMoney) {

        return (int)(payMoney * 0.7);
    }
}
