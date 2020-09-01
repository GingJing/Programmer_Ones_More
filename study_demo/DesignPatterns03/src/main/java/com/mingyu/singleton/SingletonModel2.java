package com.mingyu.singleton;

/**
 * 懒汉单例1--双检索
 *
 * @date: 2020/8/19 8:41
 * @author: GingJingDM
 * @version: 1.0
 */
public class SingletonModel2 {

    private static SingletonModel2 instance;

    private SingletonModel2() {}

    public static SingletonModel2 getInstance() {
        if (instance == null) {
            synchronized (SingletonModel2.class) {
                if (instance == null) {
                    instance = new SingletonModel2();
                }
            }
        }
        return instance;
    }

    public void sendMessage() {
        System.out.println("Lazy Singleton Model2");
    }
}
