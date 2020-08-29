package com.mingyu.completablefuture.simple;

import java.sql.SQLOutput;
import java.util.concurrent.*;

/**
 * whenComplete测试类
 *
 * @author: GingJingDM
 * @date: 2020年 08月29日 12时35分
 * @version: 1.0
 */
public class WhenCompleteTest {

    private static final ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
            8, 8, 1 ,
            TimeUnit.MINUTES, new LinkedBlockingQueue<>(10));

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        CompletableFuture<String> one = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            System.out.println("---------");
            return "one";
        });

        CompletableFuture<String> two = one.whenComplete((s, t) -> {
            if (t == null) {
                System.out.println(s + "---" + "two");
            } else {
                System.out.println(t.getLocalizedMessage());
            }
            System.out.println(Thread.currentThread().getName());
        });
        System.out.println(two.get());

        CompletableFuture<String> three = one.whenCompleteAsync((s, t) -> {
//        CompletableFuture<String> three = two.whenCompleteAsync((s, t) -> {
            System.out.println("---------");
            if (t == null) {
                System.out.println(s + "---" + "two");
            } else {
                System.out.println(t.getLocalizedMessage());
            }
            System.out.println(Thread.currentThread().getName());
        });

        System.out.println(three.get());
        CompletableFuture<String> four = one.whenCompleteAsync((s, t) -> {
//        CompletableFuture<String> four = three.whenCompleteAsync((s, t) -> {
            System.out.println("---------");
            if (t == null) {
                System.out.println(s + "---" + "two");
            } else {
                System.out.println(t.getLocalizedMessage());
            }
            System.out.println(Thread.currentThread().getName());
        }, poolExecutor);

        System.out.println(four.get());
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        // 挂起主线程等待任务线程异步执行任务完毕
        Thread.currentThread().join();

    }
}
