package com.mingyu.completablefuture.simple;

import java.util.concurrent.*;

/**
 * CompletableFuture编程式显示设置结果测试类
 *
 * @author: GingJingDM
 * @date: 2020年 08月29日 10时50分
 * @version: 1.0
 */
public class ResultSetTest {

    /** 可用CPU个数 */
    private static final int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    /** 线程池 */
    private static final ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(
            // corePoolSize
            AVALIABLE_PROCESSORS,
            // maximumPoolSize
            AVALIABLE_PROCESSORS * 2,
            // keepAliveTime
            1,
            // TimeUnit
            TimeUnit.MINUTES,
            // BlockingQueue
            new LinkedBlockingQueue<>(5),
            // RejectedExecutionHandler
            new ThreadPoolExecutor.CallerRunsPolicy()
    );

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        CompletableFuture<String> future = new CompletableFuture<>();
        POOL_EXECUTOR.execute(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(">>>>>" + Thread.currentThread().getName() + " set future result <<<<<");
            future.complete("complete!");
        });

        System.out.println("main thread is waiting future result========");
        System.out.println(future.get());
        System.out.println(future.get(1000, TimeUnit.MILLISECONDS));
        System.out.println("main thread got it");
    }
}
