package com.mingyu.strategy;


public class Vip1 implements Strategy {

    //Vip1 用户
    @Override
    public Integer money(Integer money) {
        return money;
    }
}
