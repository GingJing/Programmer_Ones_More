package com.mingyu.singleton;

/**
 * 饿汉单例
 *
 * @date: 2020/8/19 8:41
 * @author: GingJingDM
 * @version: 1.0
 */
public class SingletonModel {

    private static SingletonModel instance = new SingletonModel();

    private SingletonModel() {}

    public static SingletonModel getInstance() {
        return instance;
    }

    public void sendMessage() {
        System.out.println("Hungry Singleton Model");
    }
}
