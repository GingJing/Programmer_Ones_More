package com.mingyu.shop.service.impl;

import com.mingyu.shop.service.PayChannel;
import org.springframework.stereotype.Component;

/**
 * 微信渠道支付
 *
 * @date: 2020/9/1 12:38
 * @author: GingJingDM
 * @version: 1.0
 */
@Component("weiXinPay")
public class WeiXinPay implements PayChannel {
    @Override
    public void pay() {
        System.out.println("微信支付");
    }
}
