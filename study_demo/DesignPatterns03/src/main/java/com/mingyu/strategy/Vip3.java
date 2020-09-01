package com.mingyu.strategy;

public class Vip3 implements Strategy {

    //Vip3 用户
    @Override
    public Integer money(Integer money) {
        return (int)(money*0.5);
    }
}
