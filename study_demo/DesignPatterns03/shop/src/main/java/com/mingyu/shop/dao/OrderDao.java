package com.mingyu.shop.dao;

import com.mingyu.shop.domain.Order;

public interface OrderDao {
    int add(Order order);

    /***
     * 修改订单状态
     * @param id
     */
    void modifyStatus(String id,int status);

    /***
     * 查找订单
     * @param id
     * @return
     */
    Order findById(String id);
}
