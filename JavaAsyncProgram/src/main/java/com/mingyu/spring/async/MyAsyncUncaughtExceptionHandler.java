package com.mingyu.spring.async;



import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * Async异常处理器
 *
 * @date: 2020/9/3 8:38
 * @author: GingJingDM
 * @version: 1.0
 */
public class MyAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {
    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
        // doSomething
        System.out.println("开始处理异常");
    }
}
