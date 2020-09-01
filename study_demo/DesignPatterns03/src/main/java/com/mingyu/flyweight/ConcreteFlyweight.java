package com.mingyu.flyweight;

/**
 * 享元
 *
 * @date: 2020/8/24 9:17
 * @author: GingJingDM
 * @version: 1.0
 */
public class ConcreteFlyweight extends Flyweight {

    //接受外部状态
    public ConcreteFlyweight(String extrinsic) {
        super(extrinsic);
    }

    /**
     * 根据外部状态进行逻辑处理
     *
     * @param extrinsic 外部状态
     */
    @Override
    public void operate(int extrinsic) {
        System.out.println("具体Flyweight:"+extrinsic);
    }
}
