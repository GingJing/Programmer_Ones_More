package com.mingyu.tenet.seven.news;

public class OrderService {

    private OrderDao orderDao;
    private LogDao logDao;

    public void add(){
        orderDao.add();
        logDao.recode();
    }
}
