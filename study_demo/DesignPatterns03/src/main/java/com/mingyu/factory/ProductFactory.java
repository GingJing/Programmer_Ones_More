package com.mingyu.factory;

/**
 * 产品工厂
 *
 * @date: 2020/8/20 8:36
 * @author: GingJingDM
 * @version: 1.0
 */
public class ProductFactory {

    /**
     * 调用info方法，传入需要创建的对象的名字来创建具体对象
     *
     * @param name 名称
     * @return 产品
     */
    public Product info(String name) {
        if (name.equals("mobile")) {
            return new Mobile();
        } else if (name.equals("car")) {
            return new Car();
        } else {
            return null;
        }
    }
}
