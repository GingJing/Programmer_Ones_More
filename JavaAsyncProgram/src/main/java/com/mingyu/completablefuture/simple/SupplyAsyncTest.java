package com.mingyu.completablefuture.simple;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * SupplyAsync方法测试类
 *
 * @author: GingJingDM
 * @date: 2020年 08月29日 11时18分
 * @version: 1.0
 */
public class SupplyAsyncTest {

    private static final ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
            8, 8, 1 ,
            TimeUnit.MINUTES, new LinkedBlockingQueue<>(10));

    public static void supplyAsync() throws InterruptedException, ExecutionException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "supply get";
        });

        System.out.println(future.get());
    }

    public static void supplyAsyncWithExecutor() throws InterruptedException, ExecutionException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "supply get with executor";
        }, poolExecutor);

        System.out.println(future.get());
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        supplyAsync();
        supplyAsyncWithExecutor();
    }
}
