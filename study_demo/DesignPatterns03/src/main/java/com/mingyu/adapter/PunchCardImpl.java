package com.mingyu.adapter;

/**
 * @date: 2020/8/20 8:54
 * @author: GingJingDM
 * @version: 1.0
 */
public class PunchCardImpl implements PunchCard {
    @Override
    public void info(String name) {
        System.out.println(name + "打卡成功");
    }
}
