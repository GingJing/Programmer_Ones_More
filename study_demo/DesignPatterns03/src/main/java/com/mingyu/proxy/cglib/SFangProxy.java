package com.mingyu.proxy.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 搜房网
 *
 * @date: 2020/8/19 18:18
 * @author: GingJingDM
 * @version: 1.0
 */
public class SFangProxy implements MethodInterceptor {
    private Object instance;

    public SFangProxy(Object instance) {
        this.instance = instance;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        args[0] = "中介SFang代理租户" + args[0];
        return method.invoke(instance, args);
    }
}
