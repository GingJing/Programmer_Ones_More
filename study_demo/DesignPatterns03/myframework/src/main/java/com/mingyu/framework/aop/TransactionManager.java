package com.mingyu.framework.aop;

public class TransactionManager {
    //前置增强
    public void begin() {
        System.out.println("开启事务！");
    }
}