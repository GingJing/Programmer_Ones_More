package com.mingyu.shop.decorator;

import com.mingyu.shop.domain.Order;
import org.springframework.stereotype.Component;

/**
 * 满减优惠金额计算
 *
 * @date: 2020/8/28 8:56
 * @author: GingJingDM
 * @version: 1.0
 */
@Component("fullMoneySum")
public class FullMoneySum extends DecoratorMoneySum {

    @Override
    public void sum(Order order) {
        super.sum(order);
        moneySum(order);
    }

    /**
     * 满100减5元
     *
     * @param order 订单
     */
    public void moneySum(Order order) {
        Integer paymoney = order.getPaymoney();
        if (paymoney >= 100) {
            order.setPaymoney(paymoney - 5);
        }
    }


}
