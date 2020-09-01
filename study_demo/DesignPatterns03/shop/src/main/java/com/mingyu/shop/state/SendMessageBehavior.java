package com.mingyu.shop.state;

import com.mingyu.shop.domain.Order;
import org.springframework.stereotype.Component;

/**
 * 发送消息
 *
 * @date: 2020/9/1 13:48
 * @author: GingJingDM
 * @version: 1.0
 */
@Component("sendMessageBehavior")
public class SendMessageBehavior implements State {


    @Override
    public void doAction(Order order) {
        System.out.println("订单支付");
        order.setState(this);
    }

    @Override
    public void execute() {
        System.out.println("订单状态变更为已经支付，需要通知商家发货");
    }
}
