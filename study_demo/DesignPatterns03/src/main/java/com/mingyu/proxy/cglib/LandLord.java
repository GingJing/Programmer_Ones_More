package com.mingyu.proxy.cglib;

/**
 * 房东对象
 *
 * @date: 2020/8/19 18:01
 * @author: GingJingDM
 * @version: 1.0
 */
public class LandLord implements LandLordService {
    @Override
    public void rentingPay(String name) {
        System.out.println(name + "来交租");
    }
}
