package com.mingyu.threadpool;

import java.util.concurrent.*;


/**
 * 有返回值的简单线程池
 *
 * @author: GingJingDM
 * @date: 2020年 08月20日 22时40分
 * @version: 1.0
 */
public class CallableThreadPool {

    /** 可用进程数 */
    private static final int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    /** 线程池 */
    private static final ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(
            AVALIABLE_PROCESSORS,
            AVALIABLE_PROCESSORS * 2,
            1,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(5),
            new NamedThreadFactory("ASYNC-POOL"),
            new ThreadPoolExecutor.CallerRunsPolicy());

    private static String businessA() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "business A complete";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<String> future = POOL_EXECUTOR.submit(CallableThreadPool::businessA);
        System.out.println(future.get());
    }
}
