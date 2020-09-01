package com.mingyu.shop.service;

/**
 * 支付接口
 *
 * @date: 2020/9/1 12:28
 * @author: GingJingDM
 * @version: 1.0
 */
public interface PayService {

    /**
     * 支付
     *
     * @param type 类型
     * @param id   id
     */
    void pay(String type, String id);
}
