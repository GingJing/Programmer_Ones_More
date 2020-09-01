package com.mingyu.shop.state;

import com.mingyu.shop.domain.Order;

/**
 * 状态接口
 *
 * @date: 2020/9/1 13:47
 * @author: GingJingDM
 * @version: 1.0
 */
public interface State {

    /**
     * 变更状态
     *
     * @param order 订单
     */
    void doAction(Order order);

    /**
     * 执行行为
     */
    void execute();
}
