package com.mingyu.aop;

import org.aspectj.lang.JoinPoint;

/**
 * 日志
 *
 * @date: 2020/8/24 8:57
 * @author: GingJingDM
 * @version: 1.0
 */
public class Log {

    public void before(JoinPoint joinPoint) {
        System.out.println("执行对象：" + joinPoint.getSignature().getName());
        System.out.println("前置通知记录日志");
    }
}
