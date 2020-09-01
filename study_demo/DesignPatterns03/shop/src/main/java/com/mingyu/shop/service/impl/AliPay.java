package com.mingyu.shop.service.impl;

import com.mingyu.shop.service.PayChannel;
import org.springframework.stereotype.Component;

/**
 * 支付宝支付
 *
 * @date: 2020/9/1 12:39
 * @author: GingJingDM
 * @version: 1.0
 */
@Component("aliPay")
public class AliPay implements PayChannel {
    @Override
    public void pay() {
        System.out.println("支付宝支付");
    }
}
