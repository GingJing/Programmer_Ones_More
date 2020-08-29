package com.mingyu.completablefuture.simple;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * thenRun方法测试类
 *
 * @author: GingJingDM
 * @date: 2020年 08月29日 11时35分
 * @version: 1.0
 */
public class ThenRunTest {

    private static final ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
            8, 8, 1 ,
            TimeUnit.MINUTES, new LinkedBlockingQueue<>(10));

    public static void thenRun() throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        AtomicLong pre = new AtomicLong(0L);
        CompletableFuture<String> preFuture = CompletableFuture.supplyAsync(() -> {
            try {
//                Thread.sleep(1000);
                Thread.sleep(3000);
                pre.set(System.currentTimeMillis());
                System.out.println("preFuture sleep " + (pre.get() - start) + "mills");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " preFuture");
            return "supply get";
        });

        CompletableFuture<Void> afterFuture = preFuture.thenRun(() -> {
            try {
                long beforePreGet = System.currentTimeMillis();
                System.out.println("afterFuture before preFuture get " + (beforePreGet - start) + " mills");
                System.out.println("preFuture get: " + preFuture.get());
                long afterPre = System.currentTimeMillis();
                System.out.println("afterFuture after preFuture get " + (afterPre - beforePreGet) + " mills");
                Thread.sleep(2000);
                System.out.println("after sleep " + (System.currentTimeMillis() - afterPre) + " mills");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " after preFuture");
        });

        System.out.println(afterFuture.get());
        System.out.println(System.currentTimeMillis() - start);
    }

    public static void thenRunAsync() throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        AtomicLong pre = new AtomicLong(0L);
        CompletableFuture<String> preFuture = CompletableFuture.supplyAsync(() -> {
            try {
//                Thread.sleep(1000);
                Thread.sleep(3000);
                pre.set(System.currentTimeMillis());
                System.out.println("preFuture sleep " + (pre.get() - start) + "mills");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " preFuture");
            return "supply get";
        });

        CompletableFuture<Void> afterFuture = preFuture.thenRunAsync(() -> {
            try {
                long beforePreGet = System.currentTimeMillis();
                System.out.println("afterFuture before preFuture get " + (beforePreGet - start) + " mills");
                System.out.println("preFuture get: " + preFuture.get());
                long afterPre = System.currentTimeMillis();
                System.out.println("afterFuture after preFuture get " + (afterPre - beforePreGet) + " mills");
                Thread.sleep(2000);
                System.out.println("after sleep " + (System.currentTimeMillis() - afterPre) + " mills");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " after preFuture");
        });

        System.out.println(afterFuture.get());
        System.out.println(System.currentTimeMillis() - start);
    }

    public static void thenRunAsyncWithExecutor() throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        AtomicLong pre = new AtomicLong(0L);
        CompletableFuture<String> preFuture = CompletableFuture.supplyAsync(() -> {
            try {
//                Thread.sleep(1000);
                Thread.sleep(3000);
                pre.set(System.currentTimeMillis());
                System.out.println("preFuture sleep " + (pre.get() - start) + "mills");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " preFuture");
            return "supply get";
        });

        CompletableFuture<Void> afterFuture = preFuture.thenRunAsync(() -> {
            try {
                long beforePreGet = System.currentTimeMillis();
                System.out.println("afterFuture before preFuture get " + (beforePreGet - start) + " mills");
                System.out.println("preFuture get: " + preFuture.get());
                long afterPre = System.currentTimeMillis();
                System.out.println("afterFuture after preFuture get " + (afterPre - beforePreGet) + " mills");
                Thread.sleep(2000);
                System.out.println("after sleep " + (System.currentTimeMillis() - afterPre) + " mills");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " after preFuture");
        }, poolExecutor);

        System.out.println(afterFuture.get());
        System.out.println(System.currentTimeMillis() - start);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        thenRun();
        System.out.println("--------------------");
        thenRunAsync();
        System.out.println("--------------------");
        thenRunAsyncWithExecutor();
    }
}
