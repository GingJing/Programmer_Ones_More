package com.mingyu.shop.strategy;

import org.springframework.stereotype.Component;

/**
 * @date: 2020/8/28 9:19
 * @author: GingJingDM
 * @version: 1.0
 */
@Component("strategyVipFour")
public class StrategyVipFour implements StrategyVip {

    /**
     * 钻石会员打5折
     *
     * @param payMoney 支付金额
     * @return 优惠后支付金额
     */
    @Override
    public Integer payMoney(Integer payMoney) {

        return (int)(payMoney * 0.7);
    }
}
