package com.mingyu.threadpool;

import java.util.concurrent.*;

/**
 * 简单FutureTask类，线程池运行
 *
 * @author: GingJingDM
 * @date: 2020年 08月23日 18时09分
 * @version: 1.0
 */
public class SimpleFutureTask2 {

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


    private static String businessA() {
        try {
            Thread.sleep(2000);
            System.out.println("============>business A");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "task a";
    }

    private static String businessB() {
        try {
            Thread.sleep(2000);
            System.out.println("============>business B");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "task b";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        // 创建FutureTask任务
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            String result = null;
            try {
                result = businessA();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        });

        // 开启异步单元执行任务A
        POOL_EXECUTOR.execute(futureTask);

        // 执行任务B
        String resultB = businessB();

        // 同步等待线程A执行结束
        String resultA = futureTask.get();

        System.out.println("任务A：" + resultA + ">--------------<" + "任务B：" + resultB);
        System.out.println(System.currentTimeMillis() - start);

    }
}
