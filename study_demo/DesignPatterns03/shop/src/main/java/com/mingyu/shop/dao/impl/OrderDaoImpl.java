package com.mingyu.shop.dao.impl;


import com.mingyu.shop.dao.OrderDao;
import com.mingyu.shop.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/*****
 * @Author: http://www.itheima.com
 * @Description: com.itheima.shop.dao.impl.OrderDaoImpl
 ****/
@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /***
     * 添加订单
     */
    @Override
    public int add(Order order){
        return jdbcTemplate.update("INSERT INTO order_info(id,money,status,item_id,num,username,paymoney,couponsId) values(?,?,?,?,?,?,?,?)",order.getId(),order.getMoney(),order.getStatus(),order.getItemId(),order.getNum(),order.getUsername(),order.getPaymoney(),order.getCouponsId());
    }

    @Override
    public void modifyStatus(String id,int status) {
        jdbcTemplate.update("UPDATE order_info SET status=? WHERE id=?",status,id);
    }

    @Override
    public Order findById(String id) {
        return jdbcTemplate.queryForObject("SELECT * FROM order_info WHERE id=?",new BeanPropertyRowMapper<Order>(Order.class),id);
    }

}
