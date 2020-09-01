package com.mingyu.shop.strategy;

/**
 * vip策略接口
 *
 * @date: 2020/8/28 9:17
 * @author: GingJingDM
 * @version: 1.0
 */
public interface StrategyVip {

    /**
     * 价格计算
     *
     * @param payMoney 支付金额
     * @return 优惠后支付金额
     */
    Integer payMoney(Integer payMoney);
}
