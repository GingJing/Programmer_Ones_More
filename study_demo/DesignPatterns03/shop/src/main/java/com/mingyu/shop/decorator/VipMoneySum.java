package com.mingyu.shop.decorator;

import com.mingyu.shop.domain.Order;
import com.mingyu.shop.strategy.StrategyVip;
import com.mingyu.shop.strategy.StrategyVipFactory;
import com.mingyu.shop.user.ThreadUserLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * vip金额计算类
 *
 * @date: 2020/8/28 8:59
 * @author: GingJingDM
 * @version: 1.0
 */
@Component("vipMoneySum")
public class VipMoneySum extends DecoratorMoneySum {

    @Autowired
    private StrategyVipFactory strategyVipFactory;

    @Autowired
    private ThreadUserLog threadUserLog;

    @Override
    public void sum(Order order) {
        super.sum(order);

        vipMoneySum(order);
    }

    /**
     * vip，优惠10元
     *
     * @param order 订单
     */
    public void vipMoneySum(Order order) {

//        order.setPaymoney(order.getPaymoney() - 10);

        //获取用户等级
        Integer level = threadUserLog.get().getLevel();
        //获取价格优惠策略
        StrategyVip vipMoney = strategyVipFactory.getVip(level);
        order.setPaymoney(vipMoney.payMoney(order.getPaymoney()));
    }
}
