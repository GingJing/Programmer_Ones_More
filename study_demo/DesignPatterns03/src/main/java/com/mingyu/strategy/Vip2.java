package com.mingyu.strategy;

public class Vip2 implements Strategy {

    //Vip2 用户
    @Override
    public Integer money(Integer money) {
        return money-5;
    }
}
