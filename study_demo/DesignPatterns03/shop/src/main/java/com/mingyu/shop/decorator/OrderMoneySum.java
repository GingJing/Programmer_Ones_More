package com.mingyu.shop.decorator;

import com.mingyu.shop.dao.ItemDao;
import com.mingyu.shop.domain.Item;
import com.mingyu.shop.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 订单金额计算
 *
 * @date: 2020/8/28 8:50
 * @author: GingJingDM
 * @version: 1.0
 */
@Component("orderMoneySum")
public class OrderMoneySum implements MoneySum {

    @Autowired
    private ItemDao itemDao;

    @Override
    public void sum(Order order) {
        Item item = itemDao.findById(order.getId());
        order.setPaymoney(item.getPrice() * order.getNum());
        order.setMoney(item.getPrice() * order.getNum());
    }
}
