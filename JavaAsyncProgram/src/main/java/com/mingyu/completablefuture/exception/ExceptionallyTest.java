package com.mingyu.completablefuture.exception;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 异常测试类
 *
 * @author: GingJingDM
 * @date: 2020年 08月29日 16时28分
 * @version: 1.0
 */
public class ExceptionallyTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        new Thread(() -> {
            try {
                if (true) {
                    throw new RuntimeException("exception");
                }
                completableFuture.complete("done");
            } catch (Exception e) {
                completableFuture.completeExceptionally(e);
            }

            System.out.println(">>>>>" + Thread.currentThread().getName() + " set result");
        }, "thread-1").start();

//        System.out.println(completableFuture.get());
        System.out.println(completableFuture.exceptionally(t->"other").get());
    }
}
