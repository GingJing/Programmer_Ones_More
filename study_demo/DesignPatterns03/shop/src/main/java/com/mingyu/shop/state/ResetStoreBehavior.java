package com.mingyu.shop.state;

import com.mingyu.shop.domain.Order;

/**
 * 订单回滚
 *
 * @date: 2020/9/1 13:55
 * @author: GingJingDM
 * @version: 1.0
 */
public class ResetStoreBehavior implements State {
    @Override
    public void doAction(Order order) {
        System.out.println("订单取消");
    }

    @Override
    public void execute() {
        System.out.println("订单取消，执行库存回滚");
        System.out.println("订单取消，执行退款");
    }
}
