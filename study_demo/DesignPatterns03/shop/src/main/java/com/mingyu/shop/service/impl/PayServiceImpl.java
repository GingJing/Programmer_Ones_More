package com.mingyu.shop.service.impl;

import com.mingyu.shop.dao.OrderDao;
import com.mingyu.shop.domain.Order;
import com.mingyu.shop.service.PayChannel;
import com.mingyu.shop.service.PayFactory;
import com.mingyu.shop.service.PayService;
import com.mingyu.shop.state.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @date: 2020/9/1 12:43
 * @author: GingJingDM
 * @version: 1.0
 */
@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private PayFactory payFactory;

    @Autowired
    private State sendMessageBehavior;

    @Override
    public void pay(String type, String id) {
        Order order = orderDao.findById(id);

        PayChannel payChannel = payFactory.createChannel(type);
        payChannel.pay();

        orderDao.modifyStatus(id, 1);

        sendMessageBehavior.doAction(order);

        order.getState().execute();
    }
}
