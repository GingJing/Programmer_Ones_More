package com.mingyu.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元工厂
 *
 * @date: 2020/8/24 9:25
 * @author: GingJingDM
 * @version: 1.0
 */
public class FlyweightFactory {

    /** 定义一个容器 */
    private static Map<String, Flyweight> pool = new HashMap<>();

    /**
     * 获取flyweight
     *
     * @param extrinsic
     * @return
     */
    public static Flyweight getFlyweight(String extrinsic){
        Flyweight flyweight = null;
        //从池中获取该对象
        if(pool.containsKey(extrinsic)){
            flyweight = pool.get(extrinsic);
            System.out.println("已经存有"+extrinsic+"，直接从Pool中取");
        }else{
            //创建Flyweight
            flyweight = new ConcreteFlyweight(extrinsic);
            //将创建的Flyweight存入到Pool中
            pool.put(extrinsic,flyweight);
            System.out.println("创建"+extrinsic+",并从池中取出！");
        }
        return flyweight;
    }
}
