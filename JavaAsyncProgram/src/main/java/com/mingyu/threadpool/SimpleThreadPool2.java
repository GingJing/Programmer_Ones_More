package com.mingyu.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 简单线程池
 *
 * @author: GingJingDM
 * @date: 2020年 08月20日 22时22分
 * @version: 1.0
 */
public class SimpleThreadPool2 {
    /** 可用进程数 */
    private static final int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    /** 线程池 */
    private static final ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(
            AVALIABLE_PROCESSORS,
            AVALIABLE_PROCESSORS * 2,
            1,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(5),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        POOL_EXECUTOR.execute(() -> {
            try {
                // 耗时任务A
                bussinessA();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        POOL_EXECUTOR.execute(() -> {
            try {
                // 耗时任务B
                bussinessB();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        System.out.println(System.currentTimeMillis() - start);

        Thread.currentThread().join();
    }

    private static void bussinessA() {
        try {
            Thread.sleep(2000);
            System.out.println("a");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void bussinessB() {
        try {
            Thread.sleep(2000);
            System.out.println("b");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
