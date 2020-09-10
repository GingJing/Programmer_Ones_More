package com.mingyu.spring.async;

import org.springframework.scheduling.annotation.Async;

/**
 * Async注解测试类
 *
 * @author: GingJingDM
 * @date: 2020年 08月30日 08时51分
 * @version: 1.0
 */
public class AsyncAnnotationTest {

    @Async
    public void printMessages() {
        for (int i = 1; i <= 10; i++) {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " message" + i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Async
    public void doExceptionally() {
        System.out.println("begin");
        int i = 0;
        int j = 1 / i;
        System.out.println("not reach");
    }
}
