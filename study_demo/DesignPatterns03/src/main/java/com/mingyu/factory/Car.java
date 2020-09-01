package com.mingyu.factory;

/**
 * 汽车
 *
 * @date: 2020/8/20 8:35
 * @author: GingJingDM
 * @version: 1.0
 */
public class Car implements Product {
    @Override
    public void show() {
        System.out.println("国产车 BYD");
    }
}
