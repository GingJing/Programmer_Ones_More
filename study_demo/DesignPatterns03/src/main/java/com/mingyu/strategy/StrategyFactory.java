package com.mingyu.strategy;

import java.util.HashMap;
import java.util.Map;

public class StrategyFactory {

    //定义一个Map对象存储所有策略
    private static Map<Integer,Strategy> strategyMap = new HashMap<Integer, Strategy>();

    //初始化所有策略并存入到Map中
    static {
        strategyMap.put(1,new Vip1());
        strategyMap.put(2,new Vip2());
        strategyMap.put(3,new Vip3());
    }

    //对外提供一个方法用于根据用户提供的等级获取指定策略
    public static Strategy get(Integer level){
        return strategyMap.get(level);
    }
}