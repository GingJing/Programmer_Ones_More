package com.mingyu.adapter;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * 打卡识别器
 *
 * @date: 2020/8/20 8:55
 * @author: GingJingDM
 * @version: 1.0
 */
public class PunchCardBefore implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object o) throws Throwable {
        System.out.println("身份识别通过");
    }
}
