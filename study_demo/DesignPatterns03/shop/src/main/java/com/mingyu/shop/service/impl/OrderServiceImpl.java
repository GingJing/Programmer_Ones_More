package com.mingyu.shop.service.impl;


import com.mingyu.shop.dao.OrderDao;
import com.mingyu.shop.decorator.DecoratorMoneySum;
import com.mingyu.shop.decorator.MoneySum;
import com.mingyu.shop.domain.Order;
import com.mingyu.shop.service.ItemService;
import com.mingyu.shop.service.OrderService;
import com.mingyu.shop.state.State;
import com.mingyu.shop.user.ThreadUserLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ThreadUserLog threadUserLog;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ItemService itemService;

    @Autowired
    private MoneySum orderMoneySum;

    @Autowired
    private DecoratorMoneySum fullMoneySum;

    @Autowired
    private DecoratorMoneySum vipMoneySum;

    @Autowired
    private State resetStoreBehavior;

    /***
     * 取消订单
     * @param id
     */
    @Override
    public void cancelOrder(String id) {
        //修改订单状态
        Order order = orderDao.findById(id);
        orderDao.modifyStatus(id,2);

        //改变订单状态
        resetStoreBehavior.doAction(order);

        order.getState().execute();
    }


    /***
     * 添加订单
     * @param order
     */
    @Override
    public int add(Order order) {
        // 从共享组件中获取当前线程的用户信息，设置用户名
        order.setUsername(threadUserLog.get().getUsername());

        // 赋值
        order.setUsername("wangwu");
        //结算价格
        order.setPaymoney(100);
        //订单价格
        order.setMoney(100);

        //结算价格嵌套运算
        //对orderMoneySum进行增强【计算基础价格】,执行满减操作增强
        fullMoneySum.setMoneySum(orderMoneySum);
        //对fullMoneySum进行增强【满减操作】，执行的增强是Vip价格计算
        vipMoneySum.setMoneySum(fullMoneySum);
        vipMoneySum.sum(order);

        // 修改库存
        int mCount = itemService.modify(order.getNum(), order.getItemId());

        // 添加订单
        int addCount = orderDao.add(order);


        return addCount;
    }

}
