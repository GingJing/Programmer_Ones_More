package com.mingyu.singleton;

/**
 * 懒汉单例1--synchronized
 *
 * @date: 2020/8/19 8:41
 * @author: GingJingDM
 * @version: 1.0
 */
public class SingletonModel1 {

    private static SingletonModel1 instance;

    private SingletonModel1() {}

    public static synchronized SingletonModel1 getInstance() {
        if (instance == null) {
            instance = new SingletonModel1();
        }
        return instance;
    }

    public void sendMessage() {
        System.out.println("Lazy Singleton Model1");
    }
}
