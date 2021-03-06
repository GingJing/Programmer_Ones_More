package com.mingyu.completablefuture.spring.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * async注解demo
 *
 * @author: GingJingDM
 * @date: 2020年 08月30日 09时12分
 * @version: 1.0
 */

@Component
public class AsyncAnnotationDemo {

    @Async("myTaskExecutor")
    public void printMessages(String ss) {
        for (int i = 1; i < 6; i++) {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " message" + i + " " + ss);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Async("myTaskExecutor")
    public CompletableFuture<String> asyncDoSth(String c) {
        CompletableFuture<String> future = new CompletableFuture<>();
        try {
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + " async completable future" + " " + c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        future.complete(c + " complete it");

        return future;
    }
}
