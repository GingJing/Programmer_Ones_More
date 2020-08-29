package com.mingyu.completablefuture.simple;

import java.util.concurrent.*;

/**
 * runAsync方法测试类
 *
 * @author: GingJingDM
 * @date: 2020年 08月29日 11时06分
 * @version: 1.0
 */
public class RunAsyncTest {

    private static final ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
            8, 8, 1 ,
            TimeUnit.MINUTES, new LinkedBlockingQueue<>(10));

    public static void runAsync() throws InterruptedException, ExecutionException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("done");
        });

        System.out.println(future.get());
    }

    public static void runAsyncWithExecutor() throws InterruptedException, ExecutionException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("done with executor");
        }, poolExecutor);

        System.out.println(future.get());
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("---------------");
        runAsyncWithExecutor();
        System.out.println("=-=-=-=-=-=-=-=");
        runAsync();
    }
}
