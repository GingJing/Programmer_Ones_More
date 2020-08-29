package com.mingyu.completablefuture.simple;

import java.util.concurrent.*;

/**
 * thenAccept测试类
 *
 * @author: GingJingDM
 * @date: 2020年 08月29日 12时04分
 * @version: 1.0
 */
public class ThenAcceptTest {

    private static final ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
            8, 8, 1 ,
            TimeUnit.MINUTES, new LinkedBlockingQueue<>(10));

    public static void thenAccept() throws InterruptedException, ExecutionException {
        CompletableFuture<String> one = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "one";
        });

        CompletableFuture<Void> two = one.thenAccept(f -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("one's result: " + f);
        });

        System.out.println(two.get());
    }

    public static void thenAcceptAsync() throws InterruptedException, ExecutionException {
        CompletableFuture<String> one = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "one";
        });

        CompletableFuture<Void> two = one.thenAcceptAsync(f -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("one's result: " + f);
        });

        System.out.println(two.get());
    }

    public static void thenAcceptAsyncWithExecutor() throws InterruptedException, ExecutionException {
        CompletableFuture<String> one = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "one";
        });

        CompletableFuture<Void> two = one.thenAcceptAsync(f -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("one's result: " + f);
        }, poolExecutor);

        System.out.println(two.get());
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        thenAccept();
        System.out.println("------------");
        thenAcceptAsync();
        System.out.println("------------");
        thenAcceptAsyncWithExecutor();
    }
}
