package com.mingyu.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 七房网
 *
 * @date: 2020/8/19 18:02
 * @author: GingJingDM
 * @version: 1.0
 */
public class QFangProxy implements InvocationHandler {

    private Object instance;

    public QFangProxy(Object instance) {
        this.instance = instance;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        args[0] = "中介QFang代理租户" + args[0];
        return method.invoke(instance, args);
    }
}
